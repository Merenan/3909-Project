/**
 * ClassBean - This bean holds information on a course, to be displayed using JSPs.
 * 
 **/
package CourseCards;

public class CourseBean {
    private String names;
    private String codes; 
    private String professors; 
    private String rooms; 
    private String times; 
    private String[] days; 

    /** Get Methods **/
    public String getNames(){
        return names;
    }

    public String getCodes(){
        return codes;
    }

    public String getProfessors(){
        return professors;
    }

    public String getRooms(){
        return rooms;
    }

    public String getTimes(){
        return times;
    }

    public String[] getDays(){
        return days;
    }

    /** Set Methods **/
    public void setNames(String n){
        names = n;
    }

    public void setCodes(String c){
        codes = c;
    }

    public void setProfessors(String p){
        professors = p;
    }

    public void setRooms(String r){
        rooms = r;
    }

    public void setTimes(String t){
        times = t;
    }

    public void setDays(String[] d){
        days = d;
    }
}
