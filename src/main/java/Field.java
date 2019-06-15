import javax.print.DocFlavor;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class Field {

    //fields in a record
    private Object value;
    private int size;
    TableAttribute attribute;

    //read the field from the file
    public Field(TableAttribute attr,byte[] data){
        size = -1;
        attribute = attr;
        int the_type = attribute.getType();
        if (the_type < Util.VARCHAR){
            size = Util.DataTypeSize[the_type];
        }
        value = toValue(data);
    }


    //add a new field
    public Field(Object v,TableAttribute attr){
        value = v;
        attribute = attr;
        int type = attribute.getType();
        if (type < Util.VARCHAR){
            size = Util.DataTypeSize[type];
        }
        else{
            //varchar and string are stored in type String
            size = ((String) value).length() + 1;
        }
    }

    public int getSize() {return size;}

    public byte[] toBytes() {
        ByteArrayOutputStream byt = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byt);
        int type = attribute.getType();
        try {
            if(value != null){
                if (type == Util.CHAR) {
                    out.writeChar((char) value);
                } else if (type == Util.INT) {
                    out.writeInt((int) value);
                } else if (type == Util.FLOAT) {
                    out.writeFloat((float) value);
                } else if (type == Util.DOUBLE) {
                    out.writeDouble((double) value);
                } else if (type == Util.LONG){
                    out.writeLong((long) value);
                } else{
                    out.writeBytes((String) value + "\0");
                }
            }else{
                if (type == Util.CHAR) {
                    out.writeChar((char) 0);
                } else if (type == Util.INT) {
                    out.writeInt(Integer.MAX_VALUE);
                } else if (type == Util.FLOAT) {
                    out.writeFloat(Float.MAX_VALUE);
                } else if (type == Util.DOUBLE) {
                    out.writeDouble(Double.MAX_VALUE);
                } else if (type == Util.LONG){
                    out.writeLong(Long.MAX_VALUE);
                } else{
                    out.writeBytes("\0");
                }
            }
            out.flush();
            return byt.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object toValue(byte[] data){
        int type = attribute.getType();
        if (attribute.getPK() || attribute.getNotNull()){
            if (type == Util.CHAR) {
                return (char) data[0];
            } else if (type == Util.INT) {
                return Util.byte2int(data);
            } else if (type == Util.FLOAT){
                return Util.byte2float(data);
            } else if (type == Util.DOUBLE){
                return Util.byte2Double(data);
            } else if (type == Util.LONG){
                return Util.byte2Long(data);
            }else {
                size = data.length;
                String str = new String(data);
                return str.substring(0,str.length() - 1);
            }
        }else{
            if (type == Util.CHAR) {
                if(data[0] == (char)0)
                    return null;
                return data[0];
            } else if (type == Util.INT) {
                int t = Util.byte2int(data);
                if(t == Integer.MAX_VALUE)
                    return null;
                return t;
            } else if (type == Util.FLOAT){
                float t = Util.byte2float(data);
                if(t == Float.MAX_VALUE)
                    return null;
                return t;
            } else if (type == Util.DOUBLE){
                double t = Util.byte2Double(data);
                if(t == Double.MAX_VALUE)
                    return null;
                return t;
            } else if (type == Util.LONG){
                long t = Util.byte2Long(data);
                if(t == Long.MAX_VALUE)
                    return null;
                return t;
            }else {
                if(data[0] == '\0'){
                    size = 1;
                    return null;
                }
                size = data.length;
                String str = new String(data);
                return str.substring(0,str.length() - 1);
            }
        }
    }

    public Boolean isValid(){
        if (attribute.getPK() && value == null){
            throw new IllegalArgumentException("Primary key can not be null");
        }
        if (!attribute.getNotNull() && value == null){
            throw new IllegalArgumentException("The attribute can not be null");
        }

        if (size > attribute.getLengthLimit()){
            throw new IllegalArgumentException("The length exceeds the limit");
        }
        if(value instanceof Long){
            if(attribute.getType() != Util.LONG)
                return false;
        }else if (value instanceof Integer){
            if(attribute.getType() != Util.INT)
                return false;
        }else if(value instanceof Float){
            if(attribute.getType() != Util.FLOAT)
                return false;
        }else if(value instanceof Double){
            if(attribute.getType() != Util.DOUBLE)
                return false;
        }else if(value instanceof String){
            if(attribute.getType() != Util.STRING && attribute.getType() != Util.VARCHAR)
                return false;
        }
        return true;
    }

    public Object getValue(){
        return value;
    }

    public TableAttribute getAttribute(){return  attribute;}

    public int compareTo(Object f){
        if(this == f){
            return 0;
        }
        if(f instanceof Field){
            Field ff = (Field)f;
            int type = attribute.getType();
            int ftype = ff.attribute.getType();
            if(type != ftype){
                return 1;
            }
            if((type == Util.STRING) || (type == Util.VARCHAR)){
                String a = (String) value;
                String b = (String) ff.value;
                return a.compareTo(b);
            }
            else if(type == Util.INT){
                return (int)value-(int)ff.value;
            }
            else if(type == Util.LONG){
                 long res = (long)value-(long)ff.value;
                 if(res==0) return 0;
                 return res<0?-1:1;
            }
            else if((type == Util.FLOAT) || (type == Util.DOUBLE)){
                double res = ((Number)value).doubleValue()-((Number)ff.value).doubleValue();
                if(res == 0)return 0;
                return res<0?-1:1;
            }
        }
        return 1;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
