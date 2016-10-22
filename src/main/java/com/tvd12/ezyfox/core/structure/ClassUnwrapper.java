package com.tvd12.ezyfox.core.structure;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;

import lombok.Getter;

/**
 * Because sometimes we need serialize or deserialize an object, but using java reflection 
 * is too slow, so we need use reflection in first time to load structure of the object.
 * And this class support to hold structure of a object's class, it hold structure of 
 * getter methods
 * 
 * @author tavandung12
 *
 */

@Getter
public abstract class ClassUnwrapper extends ClassCover {
    
    protected ClassUnwrapper() {
        super();
    }
	
	// construct with java class
    public ClassUnwrapper(Class<?> clazz) {
        super(clazz);
    }

	/**
	 * Because this class is abstract, we need create instances in implementation class
	 * 
	 * @return an instance
	 */
	protected abstract ClassUnwrapper newClass();
    
	/**
	 * Add method's structure object to reference list
	 * 
	 * @param method the method to add 
	 */
    protected void addReference(GetterMethodCover method) {
        this.references.add(method);
    }
	
    /**
     * Create an instance we must specific it's reference and initialize its
     * 
     * @param clazz class to read structure
     * @param ref reference method's structure
     * @return an instance
     */
    protected ClassUnwrapper newClass(Class<?> clazz, GetterMethodCover ref) {
        ClassUnwrapper unwrapper = newClass();
        unwrapper.addReference(ref);
        unwrapper.init(clazz);
        return unwrapper;
    }
    
    /**
     * Add a method's structure to list
     * 
     * @param struct the structure of method to add
     */
    @Override
    protected void addMethod(MethodCover struct) {
        GetterMethodCover method = (GetterMethodCover)struct;
        method.setDeclaringClazz(this);
        methods.add(method);
        Class<?> paramType = null;
        if(method.isMap())
            paramType = null;
        else if(method.isTwoDimensionsObjectArray()) 
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
        method.setReturnClass(newClassUnwraper(this, paramType, method));
        
    }
    
    /**
     * Create an structure to method's returned type
     * 
     * @param declaringClass declaring class of method
     * @param paramType returned type of method
     * @param refMethod reference method of class's structure
     * @return an instance
     */
    private ClassUnwrapper newClassUnwraper(ClassUnwrapper declaringClass, 
            Class<?> paramType,
            GetterMethodCover refMethod) {
        ClassUnwrapper wrapper = checkExistsParamType(declaringClass, paramType);
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
     * @param paramType returned type of method
     * @return an instance
     */
    private ClassUnwrapper checkExistsParamType(ClassUnwrapper declaringClass, 
            Class<?> paramType) {
        Set<ClassUnwrapper> checkedClasses = new HashSet<>();
        return checkPrevious(this, paramType, checkedClasses);
    }
    
    /**
     * If we get returned type of getter method is a class type, we need check it
     * 
     * @param declaringClass declaring class of method
     * @param paramType return type of getter method
     * @param checkedClasses set of parsed classes's structure
     * @return an instance
     */
    private ClassUnwrapper checkPrevious(ClassUnwrapper declaringClass, 
            Class<?> paramType, Set<ClassUnwrapper> checkedClasses) {
        if(declaringClass.getClazz() == paramType)
            return declaringClass;
        List<GetterMethodCover> refs = declaringClass.getReferences();
        for(GetterMethodCover ref : refs) {
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
	public GetterMethodCover getMethod(int index) {
		return getMethods().get(index);
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
            ReflectMethodUtil.validateGetterMethod(method);
        } catch(IllegalStateException e) {
            return false;
        }
        return true;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.structure.ClassCover#getMethodType(java.lang.reflect.Method)
     */
    @Override
    protected Class<?> getMethodType(Method method) {
        return method.getReturnType();
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<GetterMethodCover> getMethods() {
        return (List)this.methods;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<GetterMethodCover> getReferences() {
        return (List)this.references;
    }
    
}
