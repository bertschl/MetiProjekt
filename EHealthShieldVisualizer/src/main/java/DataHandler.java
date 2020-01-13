import java.util.Map;

public class DataHandler {
    private String data;
    private String endOfDataSignature;
    private int oxy;
    private int pulse;
    private double temperature;
    private String position;
    private double conductance;
    private int bloodPressureSys;
    private int bloodPressureDias;
    private double airflow;
    private double ecg;
    private boolean messageError;//true if there is a wrong message protocoll.
    private String bufferStr;
    private String oxyString;
    private String pulseString;
    private String temperatureString;
    private String positionString;
    private String conductanceString;
    private String bloodPressureSysString;
    private String bloodPressureDiasString;
    private String airflowString;
    private String ecgString;

    public DataHandler() {
        this.data = "";
        this.endOfDataSignature = "$";
        this.oxy = 0;
        this.pulse = 0;
        this.temperature = 0;
        this.position = "non defined";
        this.conductance = 0.0;
        this.bloodPressureSys = 0;
        this.bloodPressureDias = 0;
        this.airflow = 0.0;
        this.ecg = 0.0;
        this.messageError = false;
        this.oxyString = "oxy";
        this.pulseString = "pulse";
        this.temperatureString = "temperature";
        this.positionString = "position";
        this.conductanceString = "conductance";
        this.bloodPressureSysString = "bpSys";
        this.bloodPressureDiasString = "bpDias";
        this.airflowString = "airflow";
        this.ecgString = "ecg";
    }
    public void appendData(String dataStr){
        if(! dataStr.equals("")){
            this.data += dataStr;
            //System.out.println(this.data);
            if((dataStr.substring(dataStr.length() - 1).equals(this.endOfDataSignature))) {
                String tr = this.data;
                int count = tr.length() - tr.replace("$", "").length();
                //System.out.println(count);
                if (count == 1) {
                    processDataString();
                }
                else{
                    this.data="";
                }


            }

        }
    }
    public void processDataString(){
        String withoutEnding = this.data.substring(0, this.data.length() - 1);
        //System.out.println(this.data);
        //System.out.println(withoutEnding);
        this.data="";
        String[] splitted = withoutEnding.split("=");
            this.messageError = false;
            try{
            for(int i = 0; i< splitted.length;i=i+2){
                if(splitted[i].equals(this.oxyString)){
                    this.oxy = Integer.parseInt(splitted[i+1]);
                }
                else if(splitted[i].equals(this.pulseString)){
                    this.pulse = Integer.parseInt(splitted[i+1]);
                }
                else if(splitted[i].equals(this.temperatureString)){
                    this.temperature = Double.valueOf(splitted[i+1]);
                }
                else if(splitted[i].equals(this.positionString)){
                    this.position = splitted[i+1];
                }
                else if(splitted[i].equals(this.conductanceString)){
                    this.conductance = Double.valueOf(splitted[i+1]);
                }
                else if(splitted[i].equals(this.bloodPressureDiasString)){
                    this.bloodPressureDias = Integer.parseInt(splitted[i+1]);
                }
                else if(splitted[i].equals(this.bloodPressureSysString)){
                    this.bloodPressureSys = Integer.parseInt(splitted[i+1]);
                }
                else if(splitted[i].equals(this.airflowString)){
                    this.airflow = Double.valueOf(splitted[i+1]);
                }
                else if(splitted[i].equals(this.ecgString)){
                    this.ecg = Double.valueOf(splitted[i+1]);
                }
                else{
                    //just ignore for now
                }

            }
            }
            catch(NumberFormatException e){
                e.printStackTrace();
                System.err.println("Can occur for some data transmits, if the endOfDataSifnature gets not received as a single byte transmit. For this case it can be mostly ignored as the exception gets catched and the programm is still working. If this gets at some point triggered too often than the proccessDataString() method has to be rewritten to detect such transmit anomalies(e.g. $0 instead of $ and 0)");
            }
    }
    public int getOxy() {
        return oxy;
    }

    public int getPulse() {
        return pulse;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getPosition() {
        return position;
    }

    public double getConductance() {
        return conductance;
    }

    public int getBloodPressureSys() {
        return bloodPressureSys;
    }

    public int getBloodPressureDias() {
        return bloodPressureDias;
    }

    public double getAirflow() {
        return airflow;
    }

    public double getEcg() {
        return ecg;
    }

    public String getData() {
        return data;
    }

    public String getEndOfDataSignature() {
        return endOfDataSignature;
    }
}