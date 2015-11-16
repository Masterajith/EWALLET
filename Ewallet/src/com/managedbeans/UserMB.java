package com.managedbeans;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.Column;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hbm.beans.Users;
import com.hibernate.util.HibernateUtil;
import com.mysql.fabric.xmlrpc.base.Value;
import com.mysql.jdbc.PreparedStatement;
@ManagedBean(name="UserMB")
@RequestScoped
public class UserMB implements Serializable{



public int id;


//public String name,mobileno,emailid,password; 
	
	private static final long serialVersionUID = 1L;
	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    Map<String,String> requestParams = ec.getRequestParameterMap();
    //for registration
    public String name = requestParams.get("name");
    public String emailid = requestParams.get("emailid");
    public String mobileno = requestParams.get("mobileno");
    public String password = requestParams.get("password");

    //for login
    public String loginemailid = requestParams.get("loginemailid");
    public String loginpassword = requestParams.get("loginpassword");


	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoginemailid() {
		return loginemailid;
	}

	public void setLoginemailid(String loginemailid) {
		this.loginemailid = loginemailid;
	}

	public String getLoginpassword() {
		return loginpassword;
	}

	public void setLoginpassword(String loginpassword) {
		this.loginpassword = loginpassword;
	}
	 	        public String save()
	         {
	        	  
		        Session session = HibernateUtil.getSessionFactory().openSession();
	        	 Users u=new Users();
	        	// u.setId(1);
	        	 u.setEmailid(emailid);
	        	 u.setName(name);
	        	 u.setMobileno(mobileno);
	        	u.setPassword(password);
	        	 session.beginTransaction();
	        	 session.save(u);
	        	 session.getTransaction().commit();
	        	 session.close();
				return "true";
	        	
	         }

	 	        public String validate() throws SQLException
	         {
	 	        	if(loginemailid!=null&&loginpassword!=null)
	 	        	{
	 	        	 Session session = HibernateUtil.getSessionFactory().openSession();
	 	        	 session.beginTransaction();
	 	        	
	 	        	  Query sql= session.createQuery("from Users");
	 	        		 	  List<Users> list= sql.list(); 
	 	        		 	Iterator<Users> itr=list.iterator();  
	 	        		    while(itr.hasNext()){  
	 	        		        Users q=itr.next();  
	 	        		       // System.out.println("emailid Name: "+q.getEmailid()+"password:"+q.getPassword()); 
	 	        		       if(loginemailid.equals(q.getEmailid())&&loginpassword.equals(q.getPassword()))
	 		 	        	   {
	 		 	            	   System.out.println("welcome");
	 		 	        	return "success";
	 		 	        	   }
	 	        		    }
	 	       	   session.getTransaction().commit();
	 	        	   session.close();
	 	        	}
	 	        	return "fail";
	         }
	 	        
	 	        
	        public void logout() {  
	            FacesContext.getCurrentInstance().getExternalContext()  
	                    .invalidateSession();  
	            FacesContext.getCurrentInstance()  
	                    .getApplication().getNavigationHandler()  
	                    .handleNavigation(FacesContext.getCurrentInstance(), null, "/LoginPage.xhtml");  
	        }  
	       
	 
	       
	
	
	
}
