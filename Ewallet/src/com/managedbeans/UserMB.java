package com.managedbeans;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.hibernate.Session;

import com.hbm.beans.Users;
import com.hibernate.util.HibernateUtil;
@ManagedBean(name="UserMB")
@RequestScoped
public class UserMB implements Serializable{
public int id;


//public String name,mobileno,emailid,password; 
	
	private static final long serialVersionUID = 1L;
	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    Map<String,String> requestParams = ec.getRequestParameterMap();
    public String name = requestParams.get("name");
    
    public String emailid = requestParams.get("emailid");
    public String mobileno = requestParams.get("mobileno");
    public String password = requestParams.get("password");
	
	
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
	        public void logout() {  
	            FacesContext.getCurrentInstance().getExternalContext()  
	                    .invalidateSession();  
	            FacesContext.getCurrentInstance()  
	                    .getApplication().getNavigationHandler()  
	                    .handleNavigation(FacesContext.getCurrentInstance(), null, "/LoginPage.xhtml");  
	        }  
	       
	 
	       
	
	
	
}
