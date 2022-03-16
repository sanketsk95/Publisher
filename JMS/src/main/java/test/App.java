package test;

import java.io.FileOutputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.pojo.Student;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	try{
    	    //creating the JAXB context
    	    JAXBContext jContext = JAXBContext.newInstance(Student.class);
    	    //creating the marshaller object
    	    Marshaller marshallObj = jContext.createMarshaller();
    	    Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
    	    //setting the property to show xml format output
    	    marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    	    //setting the values in POJO class
    	    Student student = new Student();
    	    student.setRoll_No(101);
    	    student.setChemistry(12);
    	    student.setEnglish(1234);
    	    student.setMaths(45);
    	    student.setPhysics(23);
    	    student.setPhysics(34);
    	    StringWriter writer = new StringWriter();
    	    //calling the marshall method
    	    marshallObj.marshal(student,new StreamResult(writer));
    	    String xml=writer.toString();
System.out.println("done"+xml);


Student student1 = (Student)unmarshallerObj.unmarshal(new StreamSource(new StringReader(xml)));
System.out.println("Unmarshalled Obj "+student1);
    	} catch(Exception e) {
    	    e.printStackTrace();
    	}
    }
}
