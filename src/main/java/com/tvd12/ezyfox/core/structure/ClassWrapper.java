package com.tvd12.ezyfox.core.structure;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;

import lombok.Getter;

/**
 * Because sometimes we need serialize or deserialize an object, but using java reflection 
 * is too slow, so we need use reflection in first time to load structure of the object.
 * And this class support to hold structure of a object's class, it hold structure of 
 * setter methods
 * 
 * @author tavandung12
 *
 */

@Getter
public abstract class ClassWrapper extends ClassCover {
    
 // list of setter method's structure
	protected List<SetterMethodCover> methods
			= new ArrayList<>();
	
	// list of setter methods's structure refer to {@code clazz}
	protected List<SetterMethodCover> references
	        = new ArrayList<>();
	
	// prevent new instance
	public ClassWrapper() {}
	
	// construct with java class
	public ClassWrapper(Class<?> clazz) {
        init(clazz);
    }
	
	/**
     * @see ClassCover#init(Class)
     */
	@Override
	public void init(Class<?> clazz) {
		super.init(clazz);
		this.initWithFields();
		this.initWithMethods();
	}
	
	/**
     * Parse annotated fields to get setter methods and their structure
     */
	private void initWithFields() {
		List<Field> fields = getAnnotatedFields();
		for(Field field : fields) {
			addMethod(initWithField(field));
		}
	}
	
	/**
     * Parse annotated methods to get setter methods and their structure
     */
	private void initWithMethods() {
		List<Method> methods = getAnnotatedMethods();
		for(Method method : methods) {
		    if(!methodFilter().filter(method))
		        continue;
			addMethod(initWithMethod(method));
		}
	}
	
	/**
     * Parse a java field to get setter method and read it's structure
     * 
     * @param field field to get setter method
     * @return a setter method's structure
     */
	protected SetterMethodCover initWithField(Field field) {return null;}
	
	/**
     * Parse java method to get it's structure
     * 
     * @param method java method
     * @return a getter method's structure
     */
	protected SetterMethodCover initWithMethod(Method method) {return null;}
	
	/**
     * Because this class is abstract, we need create instances in implementation class
     * 
     * @return an instance
     */
	protected abstract ClassWrapper newClass();
	
	/**
     * Add method's structure object to reference list
     * 
     * @param method the structure of setter method
     */
	public void addReference(SetterMethodCover method) {
	    this.references.add(method);
	}
	
	/**
     * Create an instance we must specific it's reference and initialize its
     * 
     * @param clazz class to read structure
     * @param ref reference method's structure
     * @return an instance
     */
	protected ClassWrapper newClass(Class<?> clazz, SetterMethodCover ref) {
	    ClassWrapper wrapper = newClass();
	    wrapper.addReference(ref);
	    wrapper.init(clazz);
	    return wrapper;
	}
	
	/**
     * Add a method's structure to list
     * 
     * @param method the structure of setter method
     */
	public void addMethod(SetterMethodCover method) {
	    method.setDeclaringClazz(this);
        methods.add(method);
	    Class<?> paramType = null;
	    if(method.isTwoDimensionsObjectArray()) 
            paramType = method.getComponentType().getComponentType();
	    else if(method.isObjectArray())
		    paramType = method.getComponentType();
		else if(method.isObjectCollection())
		    paramType = method.getGenericType();
		else if(method.isObject()) 
		    paramType = method.getType();
		else if(method.isArrayObjectCollection())
		    paramType = method.getGenericType().getComponentType();
		if(paramType == null) return;
		method.setParameterClass(newClassWraper(this, paramType, method));
		
	}
	
	/**
     * Create an structure to method's parameter type
     * 
     * @param declaringClass declaring class of method
     * @param paramType parameter type of method
     * @param refMethod reference method of class's structure
     * @return an instance
     */
	private ClassWrapper newClassWraper(ClassWrapper declaringClass, 
            Class<?> paramType,
            SetterMethodCover refMethod) {
	    ClassWrapper wrapper = checkExistsParamType(this, paramType);
	    if(wrapper == null)
	        return newClass(paramType, refMethod);
	    wrapper.addReference(refMethod);
	    return wrapper;
	}
	
	/**
     * Because we must read structure of all java classes that refer to any methods in {@code clazz},
     * but sometimes, which class we need parse has already parsed, so we need check its
     * 
     * @param declaringClass declaring class's structure of method
     * @param paramType parameter type of method
     * @return an instance
     */
	private ClassWrapper checkExistsParamType(ClassWrapper declaringClass, 
            Class<?> paramType) {
        Set<ClassWrapper> checkedClasses = new HashSet<>();
        return checkPrevious(this, paramType, checkedClasses);
    }
    
	/**
     * If we get parameter type of getter method is a class type, we need check it
     * 
     * @param declaringClass declaring class of method
     * @param paramType return type of getter method
     * @param checkedClasses set of parsed classes's structure
     * @return an instance
     */
    private ClassWrapper checkPrevious(ClassWrapper declaringClass, 
            Class<?> paramType, Set<ClassWrapper> checkedClasses) {
        if(declaringClass.getClazz() == paramType)
            return declaringClass;
        List<SetterMethodCover> refs = declaringClass.getReferences();
        for(SetterMethodCover ref : refs) {
            if(ref.getDeclaringClazz().getClazz() == paramType) 
                return ref.getDeclaringClazz();
            if(checkedClasses.contains(ref.getDeclaringClazz())) 
                continue;
            checkedClasses.add(ref.getDeclaringClazz());
            return checkPrevious(ref.getDeclaringClazz(), 
                        paramType, checkedClasses);
        }
        return null;
    }
	
    /**
     * Get structure object at index
     * 
     * @param index index
     * @return a structure object
     */
	public SetterMethodCover getMethod(int index) {
		return methods.get(index);
	}
    
	/**
     * @see ClassCover#methodCount()
     */
	@Override
	public int methodCount() {
		return methods.size();
	}
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.structure.ClassCover#containsMethod(java.lang.reflect.Method)
	 */
	@Override
	protected boolean containsMethod(Method method) {
	    for(MethodCover cover : methods)  {
            if(cover.getMethod().equals(method))
                return true;
        }
        return false;
	}
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.structure.ClassCover#validateMethod(java.lang.reflect.Method)
	 */
	@Override
	protected boolean validateMethod(Method method) {
	    try {
            ReflectMethodUtil.validateSetterMethod(method);
        } catch(IllegalStateException e) {
            return false;
        }
        return true;
	}
	
}
