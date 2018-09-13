package evidentierecarti;

import java.util.Date;

public class Carti {

    int id;

    public String getNumeleCartii() {
        return numeleCartii;
    }

    public void setNumeleCartii(String numeleCartii) {
        this.numeleCartii = numeleCartii;
    }

    public String getAutorulCartii() {
        return autorulCartii;
    }

    public void setAutorulCartii(String autorulCartii) {
        this.autorulCartii = autorulCartii;
    }

    public Date getDataInceperii() {
        return dataInceperii;
    }

    public void setDataInceperii(Date dataInceperii) {
        this.dataInceperii = dataInceperii;
    }

    public Date getDataTerminarii() {
        return dataTerminarii;
    }

    public void setDataTerminarii(Date dataTerminarii) {
        this.dataTerminarii = dataTerminarii;
    }

    String numeleCartii;
    String autorulCartii;
    Date dataInceperii;
    Date dataTerminarii;

    String username;
    String password;



}
