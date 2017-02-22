/**
 * ClassBean - This bean holds information on a course, to be diplayed using JSPs.
 * 
 **/

public class CourseBean {
    private String name
    private String code 
    private String professor 
    private String room 
    private String time 
    private String days 

    /** Get Methods **/
    public String getName(){
        return name;
    }

    public String getCode(){
        return code;
    }

    public String getProfessor(){
        return professor;
    }

    public String getRoom(){
        return room;
    }

    public String getTime(){
        return time;
    }

    public String getDays(){
        return days;
    }

    /** Set Methods **/
    public void setName(String n){
        name = n;
    }

    public void setCode(String c){
        code = c;
    }

    public void setProfessor(String p){
        professor = p;
    }

    public void setRoom(String r){
        room = r;
    }

    public void setTime(String t){
        time = t;
    }

    public void setDays(String d){
        days = d;
    }
}