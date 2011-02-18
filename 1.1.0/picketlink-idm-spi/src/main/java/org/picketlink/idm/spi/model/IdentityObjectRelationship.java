/*
* JBoss, a division of Red Hat
* Copyright 2006, Red Hat Middleware, LLC, and individual contributors as indicated
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
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

package org.picketlink.idm.spi.model;

/**
 * Relationship between two IdentityObject objects
 * 
 * @author <a href="mailto:boleslaw.dawidowicz at redhat.com">Boleslaw Dawidowicz</a>
 * @version : 0.1 $
 */
public interface IdentityObjectRelationship
{

   /**
    * @return relationship name or null if this relationship is not named.
    * Combination of name, fromIdentityObject and toIdentityObject must be unique - two connected
    * IdentityObject can have only one relationship with a given name  
    */
   String getName();

   /**
    *
    * @return relationship type
    */
   IdentityObjectRelationshipType getType();

   /**
    *
    * @return IdentityObject from which this relationship starts
    */
   IdentityObject getFromIdentityObject();

   /**
    * @return IdentityObject on which this relationship ends
    */
   IdentityObject getToIdentityObject();

   
}
