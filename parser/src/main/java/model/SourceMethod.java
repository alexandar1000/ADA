package model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SourceMethod {

	private String name;
	private String returnType;
	private Set<String> accessModifiers;
	// name->type
	private Map<String, String> parameters;
	private Map<String, String> usedVaribales;
	// class name->method name
	private List<String> methodCalls;

	public SourceMethod(String name, String returnType, Set<String> accessModifiers, Map<String, String> parameters,
			List<String> methodCalls, Map<String, String> usedVaribales) {
		this.name = name;
		this.returnType = returnType;
		this.accessModifiers = accessModifiers;
		this.parameters = parameters;
		this.methodCalls = methodCalls;
		this.usedVaribales = usedVaribales;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public Set<String> getAccessModifiers() {
		return accessModifiers;
	}

	public void setAccessModifiers(Set<String> accessModifiers) {
		this.accessModifiers = accessModifiers;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public Map<String, String> getUsedVaribales() {
		return usedVaribales;
	}

	public void setUsedVaribales(Map<String, String> usedVaribales) {
		this.usedVaribales = usedVaribales;
	}

	public List<String> getMethodCalls() {
		return methodCalls;
	}

	public void setMethodCalls(List<String> methodCalls) {
		this.methodCalls = methodCalls;
	}

	@Override
	public boolean equals(Object M) {
		if (M == this)
			return true;
		if (!(M instanceof SourceMethod)) {
			return false;
		}
		SourceMethod method = (SourceMethod) M;
		return method.name.equals(this.name) && method.returnType.equals(this.returnType)
				&& method.accessModifiers.equals(this.accessModifiers) && method.parameters.equals(this.parameters)
				 && method.usedVaribales.equals(this.usedVaribales)
				&& method.methodCalls.equals(this.methodCalls);
	}

	@Override
	public int hashCode() {

		int result = 17;
		result = 31 * result + this.name.hashCode();
		result = 31 * result + this.returnType.hashCode();
		result = 31 * result + this.accessModifiers.hashCode();
		result = 31 * result + this.parameters.hashCode();
		result = 31 * result + this.usedVaribales.hashCode();
		result = 31 * result + this.methodCalls.hashCode();
		return result;

	}

	@Override
	public String toString() {
		return this.returnType + " " + this.name + " " + this.parameters;
	}

}
