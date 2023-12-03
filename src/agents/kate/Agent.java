package agents.kate;

import engine.core.MarioAgent;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.MarioActions;

public class Agent implements MarioAgent {
    private enum STATE {
        WALK_FORWARD, WALK_BACKWARD, JUMP, JUMP_HOLE, STOP
    }

    private boolean facing_left;
    private int leftCounter;
//    private int shootCounter;
    private agents.kate.Agent.STATE state;
    private boolean[] action;

    @Override
    public void initialize(MarioForwardModel model, MarioTimer timer) {
        action = new boolean[MarioActions.numberOfActions()];
        state = STATE.WALK_FORWARD;
        facing_left = false;

        leftCounter = 0;
//        shootCounter = 0;

        System.out.println("Mario initialized");
    }

    //is this assuming that mario is at tile 8/16?
    private int getLocation(int relX, int relY, int[][] scene) {
        int realX = 8 + relX;
        int realY = 8 + relY;

        return scene[realX][realY];
    }


    //going to try to check way ahead
    private int getLocationEnemy(int relX, int relY, int[][] scene) {
        int realX = 10 + relX;
        int realY = 8 + relY;
        return scene[realX][realY];
    }

    private int getLocationObstacle(int relX, int relY, int[][] scene) {
        int realX = 10 + relX;
        int realY = 10 + relY;
        return scene[realX][realY];
    }

    private boolean thereIsObstacle(int[][] scene) {
        //look at space directly in front, space two in front, and the space below two in front
        int[] inFrontOf = new int[]{getLocationObstacle(1 , 0, scene), getLocationObstacle(2  , 0, scene), getLocationObstacle(2, -1, scene)};

        for (int i = 0; i < inFrontOf.length; i++) {
            if (inFrontOf[i] == 17 || inFrontOf[i] == 23 || inFrontOf[i] == 24) {
                return true;
            }
        }

        return false;
    }
    private boolean thereIsHole(int[][] scene) {
        for (int i = 1; i < 3; i++) {
            for (int j = 2; j < 8; j++) {
                if (getLocation(i, j, scene) != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean enemyInFront(int[][] enemies) {
        for (int i = 0; i > -2; i--) {
            for (int j = 1; j < 2; j++) {
                if (getLocationEnemy(j, i, enemies) > 1) {
                    System.out.println("Detected enemy in front");
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
        int[][] scene = model.getMarioSceneObservation();
        int[][] enemies = model.getMarioEnemiesObservation();
        //System.out.println(enemies);

        //urgent (return early)
        if (enemyInFront(enemies)) {
            state = STATE.WALK_BACKWARD;

            action[MarioActions.LEFT.getValue()] = false;
            action[MarioActions.RIGHT.getValue()] = true;
            action[MarioActions.SPEED.getValue()] = true;
            action[MarioActions.JUMP.getValue()] = true;


            return action;

        }
        switch (state) {
            case STOP:
                //just make mario stand
                action[MarioActions.LEFT.getValue()] = false;
                action[MarioActions.RIGHT.getValue()] = false;
                break;

            case WALK_BACKWARD:
                if (leftCounter > 5) {
                    //what the fuck
                    state = agents.kate.Agent.STATE.WALK_FORWARD;
                    facing_left = false;
                }
                leftCounter++;
                action[MarioActions.LEFT.getValue()] = true;
                action[MarioActions.RIGHT.getValue()] = false;

                break;

            case WALK_FORWARD:
                action[MarioActions.LEFT.getValue()] = false;
                if (enemyInFront(enemies)) {
                    System.out.print("Change state (cause:enemy in front): walk forward -> stop");
                    //state = STATE.STOP;
                    //action[MarioActions.SPEED.getValue()] = false;
                    action[MarioActions.LEFT.getValue()] = true;
                    action[MarioActions.RIGHT.getValue()] = false;
                }

                if (thereIsHole(scene)) {
                    System.out.print("Change state: walk forward -> jump");
                    state = agents.kate.Agent.STATE.JUMP_HOLE;
                    action[MarioActions.JUMP.getValue()] = true;
                    action[MarioActions.SPEED.getValue()] = true;
                } else if (thereIsObstacle(scene)) {
                    state = agents.kate.Agent.STATE.JUMP;
                    action[MarioActions.JUMP.getValue()] = true;
                    action[MarioActions.RIGHT.getValue()] = true;
                    action[MarioActions.SPEED.getValue()] = false;
                } else {
                    action[MarioActions.RIGHT.getValue()] = true;
                    action[MarioActions.SPEED.getValue()] = false;
                }
                break;
//
            case JUMP:
                if (action[MarioActions.RIGHT.getValue()] && thereIsHole(scene)) {
                    action[MarioActions.RIGHT.getValue()] = false;
                    action[MarioActions.LEFT.getValue()] = true;

                    facing_left = true;
                } else if (model.isMarioOnGround()) {
                    if (facing_left) {
                        state = agents.kate.Agent.STATE.WALK_BACKWARD;
                        leftCounter = 0;
                    } else {
                        state = agents.kate.Agent.STATE.WALK_FORWARD;
                    }

                    action[MarioActions.LEFT.getValue()] = false;
                    action[MarioActions.RIGHT.getValue()] = false;

                    action[MarioActions.JUMP.getValue()] = false;
                    action[MarioActions.SPEED.getValue()] = false;
                }
                break;

            case JUMP_HOLE:
                if (model.isMarioOnGround()) {
                    state = agents.kate.Agent.STATE.WALK_FORWARD;

                    action[MarioActions.JUMP.getValue()] = false;
                    action[MarioActions.SPEED.getValue()] = false;

                    action[MarioActions.LEFT.getValue()] = false;
                    action[MarioActions.RIGHT.getValue()] = false;
                }
                break;
        }

        return action;
    }

    @Override
    public String getAgentName() {
        return "KateAgent";
    }
}
