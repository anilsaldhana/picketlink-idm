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
package org.jboss.picketlink.test.idm.internal.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jboss.picketlink.idm.internal.util.IDMUtil;
import org.junit.Test;

/**
 * Unit test {@link IDMUtil}
 *
 * @author anil saldhana
 * @since Sep 13, 2012
 */
public class IDMUtilTestCase {
    @Test
    public void arrMatch() throws Exception {
        String[] a1 = { "1", "2", "3", "4" };
        String[] a2 = { "1", "3", "4", "2", };
        String[] a3 = { "2", "3", "4" };
        String[] a4 = { "3", "2", "4" };

        assertTrue(IDMUtil.arraysEqual(a1, a2));
        assertTrue(IDMUtil.arraysEqual(a3, a4));
        assertFalse(IDMUtil.arraysEqual(a1, a4));
        assertFalse(IDMUtil.arraysEqual(a2, a3));
    }
}
