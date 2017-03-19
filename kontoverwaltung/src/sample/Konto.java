package sample;

/**
 * Created by A on 02.12.2016.
 */
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

    public class Konto {

        private DoubleProperty stand;

        public final double getStand() {
            if (stand != null)
                return stand.get();
            return 0;
        }

        public final void setStand(double hoehe) {
            this.standProperty().set(hoehe);
        }

        public final DoubleProperty standProperty() {
            if (stand == null) {
                stand = new SimpleDoubleProperty(0);
            }
            return stand;
        }

        public void auszahlen (double betrag)
        {
            double rest =stand.get()-betrag ;
            if (rest >= 0)
            {
                stand.set(rest);
            }
        }
        public void einzahlen (double betrag)
        {
            if (betrag >=0)
                stand.set(stand.get()+betrag);
        }
    }

