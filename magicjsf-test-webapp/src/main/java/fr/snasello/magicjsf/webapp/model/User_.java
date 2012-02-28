package fr.snasello.magicjsf.webapp.model;

import fr.snasello.magicjsf.webapp.model.Role;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated("EclipseLink-2.0.1.v20100213-r6600 @ Sat Feb 04 16:04:30 CET 2012")
@StaticMetamodel(User.class)
public class User_ { 

	public static volatile SingularAttribute<User, Long> id;
	public static volatile SetAttribute<User, Role> roles;
	public static volatile SingularAttribute<User, String> login;

}