    package com.webook.app.domain.Enums;

    public enum ClassificacaoIndicativa {
        LIVRE(0),
        DEZ(10),
        DOZE(12),
        QUATORZE(14),
        DEZESSEIS(16),
        DEZOITO(18);

        private int i;

        ClassificacaoIndicativa(int i) {
            this.i = i;
        }

        public int getI() {
            return i;
        }
    }