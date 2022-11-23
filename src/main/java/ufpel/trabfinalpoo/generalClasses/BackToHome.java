package ufpel.trabfinalpoo.generalClasses;

import ufpel.trabfinalpoo.helperClasses.SceneManager;

public abstract class BackToHome {
    public void voltar() {
        SceneManager.sceneSet(SceneManager.SC_INICIO);
    }
}
