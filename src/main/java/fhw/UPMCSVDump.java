package fhw;

import java.util.*;
import net.revelc.gnome.keyring.*; 

public class  UPMCSVDump
{
  public static void main(String[] args) throws GnomeKeyringException {
    GnomeKeyring gk = new GnomeKeyring("UPMCSVDump");
    String keyring = gk.getDefaultKeyring();
    Set<Integer> ids = gk.getIds(keyring);
    String name;
    String userName;
    String userSecret;
    String url; 
    for(Integer id : ids)
    {
        GnomeKeyringItem gki = gk.getItem(keyring, id, true);
        //System.out.println(gki.toString(true));
        userName = null; 
        userSecret = null;
        name = null; 
        url = null; 
        //account name,user id,password,url,notes
        name = gki.getDisplayName();
        userSecret = gki.getSecret();
        for(GnomeKeyringItem.Attribute a : gki.getAttributes())
        {
            String s = a.getName();
            if("username_value".equalsIgnoreCase(s))
            {
                userName = a.getValue().toString();
            } 
            else if("action_url".equalsIgnoreCase(s))
            {
                url = a.getValue().toString();
            }
        }
        System.out.println(String.format("%s,%s,%s,%s,%s",name,userName,userSecret,url,""));
    }   
  }
}