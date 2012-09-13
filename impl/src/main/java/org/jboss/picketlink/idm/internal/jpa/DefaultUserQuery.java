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

package org.jboss.picketlink.idm.internal.jpa;

import java.util.List;
import org.jboss.picketlink.idm.model.Group;
import org.jboss.picketlink.idm.model.Role;
import org.jboss.picketlink.idm.model.SimpleGroup;
import org.jboss.picketlink.idm.model.SimpleRole;
import org.jboss.picketlink.idm.model.User;
import org.jboss.picketlink.idm.query.UserQuery;

/**
 * @author <a href="mailto:psilva@redhat.com">Pedro Silva</a>
 *
 */
public class DefaultUserQuery extends AbstractQuery<DefaultUserQuery> implements UserQuery {

    private Group relatedGroup;
    private String relatedGroupId;
    private Role role;
    private String roleName;
    private String firstName;
    private String lastName;
    private String email;

    @Override
    public List<User> executeQuery(UserQuery query) {
        return null;
    }

    @Override
    public UserQuery setRelatedGroup(Group group) {
        this.relatedGroup = group;
        return this;
    }

    @Override
    public UserQuery setRelatedGroup(String groupId) {
        this.relatedGroupId = groupId;
        return this;
    }

    @Override
    public Group getRelatedGroup() {
        if (this.relatedGroup != null) {
            return relatedGroup;
        }

        if (this.relatedGroupId == null) {
            return null;
        }

        return new SimpleGroup(null, this.relatedGroupId, null);
    }

    @Override
    public UserQuery setRole(Role role) {
        this.role = role;
        return this;
    }

    @Override
    public UserQuery setRole(String name) {
        this.roleName = name;
        return this;
    }

    @Override
    public Role getRole() {
        if (this.role != null) {
            return this.role;
        }

        if (this.roleName == null) {
            return null;
        }

        return new SimpleRole(this.roleName);
    }

    @Override
    public UserQuery setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public UserQuery setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public UserQuery setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public List<User> executeQuery() {
        throw new RuntimeException();
    }

}