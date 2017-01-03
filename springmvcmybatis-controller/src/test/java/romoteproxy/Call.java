package romoteproxy;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/29 0029.
 */
public class Call implements Serializable {
    private static final long serialVersionUID = 3389332186599415185L;
    private String className;
    private String methodName;
    @SuppressWarnings(value="unchecked")
    private Class[] paramTypes;
    private Object[] params;
    private Object result;

    public Call(){
    }
    @SuppressWarnings(value="unchecked")
    public Call(String className, String methodName,
                Class[] paramTypes, Object[] params){
        this.className = className;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.params = params;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    @SuppressWarnings(value="unchecked")
    public Class[] getParamTypes() {
        return paramTypes;
    }
    @SuppressWarnings(value="unchecked")
    public void setParamTypes(Class[] paramTypes) {
        this.paramTypes = paramTypes;
    }
    public Object[] getParams() {
        return params;
    }
    public void setParams(Object[] params) {
        this.params = params;
    }
    public Object getResult() {
        return result;
    }
    public void setResult(Object result) {
        this.result = result;
    }
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("{className=" + className);
        sb.append(",methodName=" + methodName);
        sb.append(",result=" + result);
        sb.append("}");
        return sb.toString();
    }
}
