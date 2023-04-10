package Manage;

public class Fps extends Thread {
    GameStateManager gSM;
    long startTime;
    double delay;

    public Fps(GameStateManager gSM) {
        this.gSM = gSM;
        startTime = System.currentTimeMillis();
        delay = (double)1000/60;       //60fps
    }

    public void update() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (elapsedTime >= delay) {
            if(gSM.isChangedMode()){
                gSM.setMusicController(gSM.getMusicController());
                gSM.setChangedMode(false);
            }
            switch (gSM.getCurrentGameState()){
                case GAME:
                    gSM.getGamePanel().repaint();
                    break;
                case MENU:
                    gSM.getGameMenu().repaint();
                    break;
                case SETTINGS:
                    gSM.getGameSettings().repaint();
                    break;
                case MULTIPLAYER:
                    gSM.getGameMultiplayer().repaint();
                    break;
                case GAMEOVER:
                    gSM.getGameOver().repaint();
                    break;
                case HOWTOPLAY:
                    gSM.getGameHowToPlay().repaint();
                    break;
            }
            startTime = System.currentTimeMillis();
        }
    }

    @Override
    public void run() {
        while(true) {
            update();

            try {
                Thread.sleep(1);
            }
            catch (InterruptedException e) {
                 throw new RuntimeException(e);
            }
        }
    }
}
