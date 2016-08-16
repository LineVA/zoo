package launch.options;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author doyenm
 */
public class Option {
    
    Locale locale;
    
    ResourceBundle generalCmdBundle;
    
    public Option(){
        locale = Locale.getDefault();
        this.generalCmdBundle = ResourceBundle.getBundle("i18n.info");
    }
    
    public ResourceBundle getGeneralCmdBundle(){
        return generalCmdBundle;
    }
    
    public void setLanguage(String lang){
        if(lang.equals("en")){
            locale = new Locale("");
            this.generalCmdBundle = ResourceBundle.getBundle("i18n.info", locale);
        } else if(lang.equals("fr")){
             locale = new Locale("fr");
            this.generalCmdBundle = ResourceBundle.getBundle("i18n.info", locale);
        }
    }
    
    
    
}
