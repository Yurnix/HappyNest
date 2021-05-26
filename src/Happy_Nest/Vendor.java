/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Happy_Nest;

import java.util.Vector;
import java.util.Date; 


public class Vendor
{
    private String name;
    private String description;
    private Date creation_date = new Date();
    private Vector < String > categoryName = new Vector < String >();
    private Vector < Vector < Product > > category = new Vector < Vector < Product > >();
    
    //private Vector < Product > products = new Vector < Product >();
    
    public Vendor (String sname, String sdesc)
    {
        setName(sname);
        setDescription(sdesc);
        setDate(System.currentTimeMillis());
        addCategory("Catalogue");
    }
    public Vendor ()
    {
        setName("Noname");
        setDescription("");
        setDate(new Date());
        //addCategory("Catalogue");
    }
    public long getCreationTime()
    {
        return creation_date.getTime();
    }
    
    public void addCategory(String name)
    {
        if(!name.equals(""))
        {
            categoryName.add(name);
            category.add(new Vector < Product > ());
        }
    }
    
    public int Comparator (Vendor a)
    {
        return getName().compareToIgnoreCase(a.getName());
    }
    
    @Override
    public String toString()
    {
           return name;     
    }
    public Product getProduct(int cat, int index)
    {
        return category.get(cat).get(index);
    }
    
    public int getCategoriesNum()
    {
        return category.size();
    }
    
    public String getCategoryName(int cat)
    {
        return categoryName.get(cat);
    }
    
    public int getProductsNum(int cat)
    {
        return category.get(cat).size();
    }
    public String getName()
    {
        return name;
    }
    public String getDesc()
    {
        return description;
    }
    
    public void removeAt(int cat,int index)
    {
        category.get(cat).remove(index);
    }
    public String getSDate()
    {
        return String.valueOf(creation_date.getDate()) +
        "/" +
        String.valueOf(creation_date.getMonth() + 1) +
        "/" +
        String.valueOf(creation_date.getYear() + 1900);
    }
    
    public void setName(String sname)
    {
        name = sname;
    }
    
    public void setDescription(String sdescription)
    {
        description = sdescription;
    }
    
    public void setDate(Date sdate)
    {
        creation_date = sdate;
    }
    public void setDate(long time)
    {
        creation_date.setTime(time);
    }
    public void addProduct(int cat, Product aproduct)
    {
        category.get(cat).addElement(aproduct);
    }
    
    public void sortByName(int cat)
    {
        Product temp = new Product();
        String a, b;
        
        for(int i = 0;i < category.get(cat).size(); i++)
            for(int j = i + 1; j < category.get(cat).size(); j++)
            {
                a = category.get(cat).get(i).getName();
                b = category.get(cat).get(j).getName();
                if (compare(a, b))
                {
                    temp = category.get(cat).get(i);
                    category.get(cat).set(i,category.get(cat).get(j));
                    category.get(cat).set(j,temp);
                }
            }
                    
    }
    
    private boolean compare(String a,String b) // lexicografically a > b ?
    {
        int i;
        
        //find min length
        int at = a.length();
        if (at > b.length())
            at = b.length();
        
        //compare
        for(i = 0; i < at; i++)
        {
            if(a.charAt(i) > b.charAt(i))
                return true;
            if(a.charAt(i) < b.charAt(i))
                return false;
        }
        if(a.length() > b.length())
            return true;
        return false;
    }
    
    public void sortByDate(int cat)
    {
        Product temp = new Product();
        for(int i = 0;i < category.get(cat).size(); i++)
            for(int j = i + 1; j < category.get(cat).size(); j++)
            {
                if(category.get(cat).get(i).getTime() > category.get(cat).get(j).getTime())
                {
                    temp = category.get(cat).get(i);
                    category.get(cat).set(i,category.get(cat).get(j));
                    category.get(cat).set(j,temp);
                }
            }
    }

    void setCategoryName(int index, String catName) {
        categoryName.setElementAt(catName, index);
    }

    void removeCategory(int cat) {
        categoryName.remove(cat);
        category.remove(cat);
    }
}
    