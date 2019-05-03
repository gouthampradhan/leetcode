package depth_first_search;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by gouthamvidyapradhan on 08/03/2019 Given a robot cleaner in a room modeled as a grid.
 *
 * <p>Each cell in the grid can be empty or blocked.
 *
 * <p>The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it
 * made is 90 degrees.
 *
 * <p>When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays
 * on the current cell.
 *
 * <p>Design an algorithm to clean the entire room using only the 4 given APIs shown below.
 *
 * <p>interface Robot { // returns true if next cell is open and robot moves into the cell. //
 * returns false if next cell is obstacle and robot stays on the current cell. boolean move();
 *
 * <p>// Robot will stay on the same cell after calling turnLeft/turnRight. // Each turn will be 90
 * degrees. void turnLeft(); void turnRight();
 *
 * <p>// Clean the current cell. void clean(); } Example:
 *
 * <p>Input: room = [ [1,1,1,1,1,0,1,1], [1,1,1,1,1,0,1,1], [1,0,1,1,1,1,1,1], [0,0,0,1,0,0,0,0],
 * [1,1,1,1,1,1,1,1] ], row = 1, col = 3
 *
 * <p>Explanation: All grids in the room are marked by either 0 or 1. 0 means the cell is blocked,
 * while 1 means the cell is accessible. The robot initially starts at the position of row=1, col=3.
 * From the top left corner, its position is one row below and three columns right. Notes:
 *
 * <p>The input is only given to initialize the room and the robot's position internally. You must
 * solve this problem "blindfolded". In other words, you must control the robot using only the
 * mentioned 4 APIs, without knowing the room layout and the initial robot's position. The robot's
 * initial position will always be in an accessible cell. The initial direction of the robot will be
 * facing up. All accessible cells are connected, which means the all cells marked as 1 will be
 * accessible by the robot. Assume all four edges of the grid are all surrounded by wall.
 *
 * <p>Solution: O(N x M) Maintain a direction and position of robot in each cell and perform a dfs
 * to clean all rooms. Important to note here is that during call back in the dfs recursion the
 * robot has to return back to its original cell and orientation
 */
public class RobotRoomCleaner {

  // direction
  // UP 0, LEFT = 1, DOWN = 2, RIGHT = 3

  private final int[] R = {-1, 0, 1, 0};
  private final int[] C = {0, -1, 0, 1};

  interface Robot {
    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
    public boolean move();

    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    public void turnLeft();

    public void turnRight();

    // Clean the current cell.
    public void clean();
  }

  static class Position {
    int r, c;

    Position(int r, int c) {
      this.r = r;
      this.c = c;
    }

    @Override
    public boolean equals(Object obj) {
      int r = ((Position) obj).r;
      int c = ((Position) obj).c;
      return (this.r == r && this.c == c);
    }

    @Override
    public int hashCode() {
      return Objects.hash(r, c);
    }
  }

  private static Set<Position> done;

  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {}

  public void cleanRoom(Robot robot) {
    done = new HashSet<>();
    dfs(1, 3, done, robot, 0);
  }

  private void dfs(int r, int c, Set<Position> done, Robot robot, int direction) {
    done.add(new Position(r, c));
    robot.clean();
    for (int i = 0; i < 4; i++) {
      int newR = r + R[direction];
      int newC = c + C[direction];
      if (!done.contains(new Position(newR, newC))) {
        boolean possible = robot.move();
        if (possible) {
          dfs(newR, newC, done, robot, direction);
        }
      }
      robot.turnLeft();
      direction = (direction + 1) % 4;
    }
    robot.turnLeft();
    robot.turnLeft();
    robot.move();
    robot.turnLeft();
    robot.turnLeft();
  }
}
