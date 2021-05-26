/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Happy_Nest;


import java.util.Date;

public class Product 
{
    private String name;
    private String description;
    private float cost;
    private int tax;
    private Date cdate = new Date();
    
    public Product()
    {
        setName("Empty");
        setDescription("No description");
        setCost(-1);
        setTax(0);
        setDate(System.currentTimeMillis());
    }
    public Product(String n, String d, float c, int t)
    {
        setDate(System.currentTimeMillis());
        setName(n);
        setDescription(d);
        setCost(c);
        setTax(t);
    }
    
    public void setTax(int t)
    {
        tax = t;
    }
    
    public int getTax()
    {
        return tax;
    }
    
    public long getCreationTime()
    {
        return cdate.getTime();
    }
     
    public String getName()
    {
        return name;
    }
    public String getDescription()
    {
        return description;
    }
    public float getCost()
    {
        return cost;
    }
    public Date getDate()
    {
        return cdate;
    }
    
    public void setDate(long time)
    {
        cdate.setTime(time);
    }
    
    public void setDate(Date cd)
    {
        cdate = cd;
    }
    
    public void setName(String n)
    {
        name = n;
    }
    public void setDescription(String d)
    {
        description = d;
    }
    public void setCost(float c)
    {
        cost = c;
    }
    
    public String getSDate()
    {
        return String.valueOf(cdate.getDate()) +
        "/" +
        String.valueOf(cdate.getMonth() + 1) +
        "/" +
        String.valueOf(cdate.getYear() + 1900);
    }
    
    public long getTime()
    {
        return cdate.getTime();
    }
    
}
