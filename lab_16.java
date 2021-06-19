package com.example.helloworld;

    interface EnergyMax{
         void port();
         void ChargingDefault();
         void StopChargingDefault();
    }

    interface EnergyNormal{
         void port();
         void CustomCharging();
         void StopCustomCharging();
    }

    class Future implements EnergyMax{


        @Override
        public void port(){
            System.out.println("charging starts");
        }

        @Override
        public void ChargingDefault(){
            System.out.println("charge with 380v voltage");
        }

        @Override
        public void StopChargingDefault(){
            System.out.println("charging stopped");
        }
    }

    class Nowdays implements EnergyNormal{

        @Override
        public void port(){
            System.out.println("Custom charging starts");
        }

        @Override
        public void CustomCharging(){
            System.out.println("charge with 220v voltage");
        }

        @Override
        public void StopCustomCharging(){
            System.out.println("charging stopped");
        }
    }

    class PortAdapter implements EnergyMax{
        EnergyNormal EnergyNormal;

        public PortAdapter(EnergyNormal EnergyNormal){
            this.EnergyNormal = EnergyNormal;
        }

        @Override
        public void port(){
            EnergyNormal.port();
        }

        @Override
        public void ChargingDefault(){
            EnergyNormal.CustomCharging();
        }

        @Override
        public void StopChargingDefault(){
            EnergyNormal.StopCustomCharging();
        }
    }

    class CentralProcessor{
        private EnergyMax phone;
        public CentralProcessor(EnergyMax phone){
            this.phone = phone;
        }

        public void work(){
            phone.port();
            phone.ChargingDefault();
            phone.StopChargingDefault();
        }
    }

    public class lab_16{
        public static void main(String[] args){
            EnergyMax future = new Future();
            CentralProcessor cp = new CentralProcessor(future);
            cp.work();
            PortAdapter nowdays = new PortAdapter(new Nowdays());
            CentralProcessor cpRus = new CentralProcessor(nowdays);
            cpRus.work();
        }
    }

