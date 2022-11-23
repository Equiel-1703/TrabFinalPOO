package ufpel.trabfinalpoo.generalClasses;

import java.security.InvalidParameterException;

public enum Aprovacao {

    NOT_SET("Não avaliado"), APPROVED("Aprovado"), REJECTED("Rejeitado");

    private String desc;
    Aprovacao(String desc)
    {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.desc;
    }

    public static Aprovacao convertToEnum(String s) throws InvalidParameterException {
        for(Aprovacao a : Aprovacao.values())
        {
            if(a.desc.equals(s))
                return a;
        }

        throw new InvalidParameterException("O valor " + s + " não é reconhecido pelo programa como um valor válido de estado de aprovação.");
    }
}
