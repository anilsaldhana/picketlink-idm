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

package org.picketlink.idm.internal.jpa;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.picketlink.idm.model.Role;

/**
 * <p>
 * Implementation of {@link AbstractDatabaseAttribute} to manage {@link Role} attributes.
 * </p>
 *
 * @author <a href="mailto:psilva@redhat.com">Pedro Silva</a>
 *
 */
@Entity
public class DatabaseRoleAttribute extends AbstractDatabaseAttribute<DatabaseRole> {

    @ManyToOne
    private DatabaseRole role;

    public DatabaseRoleAttribute() {

    }

    public DatabaseRoleAttribute(String name, String value) {
        super(name, value);
    }

    /**
     * @return the role
     */
    public DatabaseRole getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(DatabaseRole role) {
        this.role = role;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.picketlink.idm.internal.jpa.AbstractDatabaseAttribute#getIdentityType()
     */
    @Override
    protected DatabaseRole getIdentityType() {
        return this.role;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.picketlink.idm.internal.jpa.AbstractDatabaseAttribute#setIdentityType(org.picketlink.idm.model.IdentityType )
     */
    @Override
    protected void setIdentityType(DatabaseRole identityType) {
        this.role = identityType;
    }

}
