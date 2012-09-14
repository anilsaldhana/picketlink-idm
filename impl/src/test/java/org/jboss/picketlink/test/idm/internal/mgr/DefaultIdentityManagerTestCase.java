/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.picketlink.test.idm.internal.mgr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

import org.jboss.picketlink.idm.internal.DefaultIdentityManager;
import org.jboss.picketlink.idm.internal.LDAPIdentityStore;
import org.jboss.picketlink.idm.internal.config.LDAPConfiguration;
import org.jboss.picketlink.idm.internal.config.LDAPConfigurationBuilder;
import org.jboss.picketlink.idm.internal.util.Base64;
import org.jboss.picketlink.idm.model.User;
import org.jboss.picketlink.idm.query.UserQuery;
import org.jboss.picketlink.idm.spi.IdentityStoreConfigurationBuilder;
import org.junit.Before;
import org.junit.Test;
import org.picketbox.test.ldap.AbstractLDAPTest;

/**
 * Unit test the {@link DefaultIdentityManager}
 *
 * @author anil saldhana
 * @since Sep 6, 2012
 */
public class DefaultIdentityManagerTestCase extends AbstractLDAPTest {

    @Before
    public void setup() throws Exception {
        super.setup();
        importLDIF("ldap/users.ldif");
    }

    @Test
    public void testDefaultIdentityManager() throws Exception {

        LDAPIdentityStore store = new LDAPIdentityStore();
        store.setConfiguration(getConfiguration());

        DefaultIdentityManager im = new DefaultIdentityManager();
        im.setIdentityStore(store); // TODO: wiring needs a second look

        // Let us create an user
        User user = im.createUser("Anil Saldhana");
        assertNotNull(user);

        User anil = im.getUser("Anil Saldhana");
        assertNotNull(anil);
        assertEquals("Anil Saldhana", anil.getFullName());
        assertEquals("Anil", anil.getFirstName());
        assertEquals("Saldhana", anil.getLastName());

        // Deal with Anil's attributes
        anil.setAttribute("QuestionTotal", "2");
        anil.setAttribute("Question1", "What is favorite toy?");
        anil.setAttribute("Question1Answer", "Gum");

        anil.setAttribute("Question2", "What is favorite word?");
        anil.setAttribute("Question2Answer", "Hi");

        // Certificate
        InputStream bis = getClass().getClassLoader().getResourceAsStream("cert/servercert.txt");

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert = (X509Certificate) cf.generateCertificate(bis);
        bis.close();

        String encodedCert = Base64.encodeBytes(cert.getEncoded());
        anil.setAttribute("x509", encodedCert);

        // let us retrieve the attributes from ldap store and see if they are the same
        anil = im.getUser("Anil Saldhana");
        Map<String, String[]> attributes = anil.getAttributes();
        assertNotNull(attributes);

        assertEquals("2", attributes.get("QuestionTotal")[0]);
        assertEquals("What is favorite toy?", attributes.get("Question1")[0]);
        assertEquals("Gum", attributes.get("Question1Answer")[0]);
        assertEquals("What is favorite word?", attributes.get("Question2")[0]);
        assertEquals("Hi", attributes.get("Question2Answer")[0]);

        String loadedCert = attributes.get("x509")[0];
        byte[] certBytes = Base64.decode(loadedCert);

        cert = (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(certBytes));
        assertNotNull(cert);

        // Change password
        String anilpass = "testpass";
        im.updatePassword(anil, anilpass);

        // Let us validate
        assertTrue(im.validatePassword(anil, anilpass));

        assertFalse(im.validatePassword(anil, "BAD"));

        // Let us do UserQuery search
        UserQuery query = im.createUserQuery().setAttributeFilter("QuestionTotal", new String[] { "2" });

        List<User> returnedUsers = query.executeQuery();
        assertNotNull(returnedUsers);
        assertEquals(1, returnedUsers.size());

        im.removeUser(anil);
        anil = im.getUser("Anil Saldhana");
        assertNull(anil);

    }

    private LDAPConfiguration getConfiguration() {
        String fqn = LDAPConfigurationBuilder.class.getName();
        LDAPConfiguration config = (LDAPConfiguration) IdentityStoreConfigurationBuilder.config(fqn);

        config.setBindDN(adminDN).setBindCredential(adminPW).setLdapURL("ldap://localhost:10389");
        config.setUserDNSuffix("ou=People,dc=jboss,dc=org").setRoleDNSuffix("ou=Roles,dc=jboss,dc=org");
        config.setGroupDNSuffix("ou=Groups,dc=jboss,dc=org");
        return config;
    }
}