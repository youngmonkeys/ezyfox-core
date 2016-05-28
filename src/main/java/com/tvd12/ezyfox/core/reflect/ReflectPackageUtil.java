package com.tvd12.ezyfox.core.reflect;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

/**
 * Utilities for working with {@link Package}s by reflection
 * 
 * @author tavandung12
 *
 */

public final class ReflectPackageUtil {
	
    // prevent new instance
	private ReflectPackageUtil() {
	}

	/**
	 * Find all classes be annotated with a annotation in a package
	 * 
	 * @param pkg package to scan
	 * @param annotation annotation class
	 * @return set of classes
	 */
	public static Set<Class<?>> findClasses(String pkg, 
			Class<? extends Annotation> annotation) {
		Reflections reflections = new Reflections(pkg);
		return reflections.getTypesAnnotatedWith(annotation);
	}
	
	/**
	 * Find all classes be annotated with a annotation in some packages
	 * 
	 * @param pkgs array of packages to scan
	 * @param annotation annotation class
	 * @return list of classes
	 */
	public static List<Class<?>> findClasses(String[] pkgs, 
			Class<? extends Annotation> annotation) {
		List<Class<?>> result = new ArrayList<>();
		for(String pkg : pkgs) {
			result.addAll(findClasses(pkg, annotation));
		}
		return result;
	}

}
