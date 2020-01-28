package model;

import java.util.Map;
import java.util.Set;

public class SourceFile {

	private String className;
	private String parentClassName;
	private Set<String> implementedInterface;
	private Map<String, String> staticVaribales;
	private Set<SourceMethod> methods;


	public SourceFile(String className, String parentClassName, Set<String> implementedInterface, Map<String, String> staticVaribales, Set<SourceMethod> methods) {
		this.className = className;
		this.parentClassName = parentClassName;
		this.implementedInterface = implementedInterface;
		this.staticVaribales = staticVaribales;
		this.methods = methods;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getParentClassName() {
		return parentClassName;
	}

	public void setParentClassName(String parentClassName) {
		this.parentClassName = parentClassName;
	}

	public Set<String> getImplementedInterface() {
		return implementedInterface;
	}

	public void setImplementedInterface(Set<String> implementedInterface) {
		this.implementedInterface = implementedInterface;
	}

	public Map<String, String> getStaticVaribales() {
		return staticVaribales;
	}

	public void setStaticVaribales(Map<String, String> staticVaribales) {
		this.staticVaribales = staticVaribales;
	}

	public Set<SourceMethod> getMethods() {
		return methods;
	}

	public void setMethods(Set<SourceMethod> methods) {
		this.methods = methods;
	}

	@Override
	public boolean equals(Object cl) {
		if (cl == this)
			return true;
		if (!(cl instanceof SourceFile)) {
			return false;
		}
		SourceFile clas = (SourceFile) cl;
		return clas.className.equals(this.className) && clas.parentClassName.equals(this.parentClassName)
				&& clas.implementedInterface.equals(this.implementedInterface)	&& clas.staticVaribales.equals(this.staticVaribales)&& clas.methods.equals(this.methods);
	}

	@Override
	public int hashCode() {

		int result = 17;
		result = 31 * result + className.hashCode();
		result = 31 * result + parentClassName.hashCode();
		result = 31 * result + implementedInterface.hashCode();
		result = 31 * result + staticVaribales.hashCode();
		result = 31 * result + methods.hashCode();
		return result;

	}

	@Override
	public String toString() {
		return this.className;
	}

}
