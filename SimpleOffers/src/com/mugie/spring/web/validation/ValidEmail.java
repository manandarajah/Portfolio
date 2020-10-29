package com.mugie.spring.web.validation;

/*
 * Bean Validation API
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size.List;

/**
 * The annotated element size must be between the specified boundaries (included).
 * <p>
 * Supported types are:
 * <ul>
 *     <li>{@code CharSequence} (length of character sequence is evaluated)</li>
 *     <li>{@code Collection} (collection size is evaluated)</li>
 *     <li>{@code Map} (map size is evaluated)</li>
 *     <li>Array (array length is evaluated)</li>
 * </ul>
 * <p>
 * {@code null} elements are considered valid.
 *
 * @author Emmanuel Bernard
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = com.mugie.spring.web.validation.ValidEmailImpl.class )
public @interface ValidEmail {

	String message() default "This doesn't appear to be a valid email address";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	/**
	 * @return size the element must be higher or equal to
	 */
	int min() default 5;
}