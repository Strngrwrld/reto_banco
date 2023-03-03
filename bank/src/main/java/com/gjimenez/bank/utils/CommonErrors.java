package com.gjimenez.bank.utils;

public enum CommonErrors {
    OK {
        @Override
        public String getMensaje() {
            return "Operacion exitosa";
        }

        @Override
        public int getCodigo() {
            return 200;
        }
    }, NOT_FOUND{
        @Override
        public String getMensaje() {
            return "Recurso no existe";
        }

        @Override
        public int getCodigo() {
            return 404;
        }
    }, BAD_REQUEST{
        @Override
        public String getMensaje() {
            return "Petición mal formulada";
        }

        @Override
        public int getCodigo() {
            return 403;
        }
    };

    public String getMensaje(){
        return "Default message";
    }
    public int getCodigo(){
        return 200;
    }
}
