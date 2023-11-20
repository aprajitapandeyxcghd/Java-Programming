import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game2048 extends JFrame {
    private final int ROWS = 4;
    private final int COLS = 4;
    private final int SCALE = 100;
    private final int GAP = 10;
    private JButton[][] grid = new JButton[ROWS][COLS];
    private boolean canMove = true;

    public Game2048() {
        setTitle("2048 Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(COLS * SCALE + GAP, ROWS * SCALE + GAP);
        setResizable(false);
        setLayout(new GridLayout(ROWS, COLS, GAP, GAP));

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                grid[i][j] = new JButton();
                grid[i][j].setPreferredSize(new Dimension(SCALE, SCALE));
                add(grid[i][j]);
            }
        }

        newGame();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (!canMove) return;

                int code = e.getKeyCode();
                if (code == KeyEvent.VK_UP) {
                    moveUp();
                } else if (code == KeyEvent.VK_DOWN) {
                    moveDown();
                } else if (code == KeyEvent.VK_LEFT) {
                    moveLeft();
                } else if (code == KeyEvent.VK_RIGHT) {
                    moveRight();
                }
            }
        });

        setVisible(true);
    }

    private void newGame() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                grid[i][j].setText("");
            }
        }

        addRandom();
        addRandom();
        canMove = true;
    }

    private void addRandom() {
        int num = (int) (Math.random() * 10);
        if (num < 7) num = 2;
        else num = 4;

        boolean valid = false;
        while (!valid) {
            int randomRow = (int) (Math.random() * ROWS);
            int randomCol = (int) (Math.random() * COLS);

            if (grid[randomRow][randomCol].getText().equals("")) {
                grid[randomRow][randomCol].setText(String.valueOf(num));
                valid = true;
            }
        }
    }

    private void moveUp() {
        canMove = false;
        for (int j = 0; j < COLS; j++) {
            for (int i = 1; i < ROWS; i++) {
                if (!grid[i][j].getText().equals("")) {
                    int k;
                    for (k = i - 1; k >= 0; k--) {
                        if (grid[k][j].getText().equals("")) {
                            break;
                        }
                        if (Integer.parseInt(grid[k][j].getText()) == Integer.parseInt(grid[i][j].getText())) {
                            int result = Integer.parseInt(grid[k][j].getText()) + Integer.parseInt(grid[i][j].getText());
                            grid[k][j].setText(String.valueOf(result));
                            grid[i][j].setText("");
                            canMove = true;
                            break;
                        }
                    }
                    if (k != i - 1) {
                        grid[k + 1][j].setText(grid[i][j].getText());
                        grid[i][j].setText("");
                    }
                }
            }
        }
        if (canMove) {
            addRandom();
            checkLose();
        }
    }

    private void moveDown() {
        canMove = false;
        for (int j = 0; j < COLS; j++) {
            for (int i = ROWS - 2; i >= 0; i--) {
                if (!grid[i][j].getText().equals("")) {
                    int k;
                    for (k = i + 1; k < ROWS; k++) {
                        if (grid[k][j].getText().equals("")) {
                            break;
                        }
                        if (Integer.parseInt(grid[k][j].getText()) == Integer.parseInt(grid[i][j].getText())) {
                            int result = Integer.parseInt(grid[k][j].getText()) + Integer.parseInt(grid[i][j].getText());
                            grid[k][j].setText(String.valueOf(result));
                            grid[i][j].setText("");
                            canMove = true;
                            break;
                        }
                    }
                    if (k != i + 1) {
                        grid[k - 1][j].setText(grid[i][j].getText());
                        grid[i][j].setText("");
                    }
                }
            }
        }
        if (canMove) {
            addRandom();
            checkLose();
        }
    }

    private void moveLeft() {
        canMove = false;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 1; j < COLS; j++) {
                if (!grid[i][j].getText().equals("")) {
                    int k;
                    for (k = j - 1; k >= 0; k--) {
                        if (grid[i][k].getText().equals("")) {
                            break;
                        }
                        if (Integer.parseInt(grid[i][k].getText()) == Integer.parseInt(grid[i][j].getText())) {
                            int result = Integer.parseInt(grid[i][k].getText()) + Integer.parseInt(grid[i][j].getText());
                            grid[i][k].setText(String.valueOf(result));
                            grid[i][j].setText("");
                            canMove = true;
                            break;
                        }
                    }
                    if (k != j - 1) {
                        grid[i][k + 1].setText(grid[i][j].getText());
                        grid[i][j].setText("");
                    }
                }
            }
        }
        if (canMove) {
            addRandom();
            checkLose();
        }
    }

    private void moveRight() {
        canMove = false;
        for (int i = 0; i < ROWS; i++) {
            for (int j = COLS - 2; j >= 0; j--) {
                if (!grid[i][j].getText().equals("")) {
                    int k;
                    for (k = j + 1; k < COLS; k++) {
                        if (grid[i][k].getText().equals("")) {
                            break;
                        }
                        if (Integer.parseInt(grid[i][k].getText()) == Integer.parseInt(grid[i][j].getText())) {
                            int result = Integer.parseInt(grid[i][k].getText()) + Integer.parseInt(grid[i][j].getText());
                            grid[i][k].setText(String.valueOf(result));
                            grid[i][j].setText("");
                            canMove = true;
                            break;
                        }
                    }
                    if (k != j + 1) {
                        grid[i][k - 1].setText(grid[i][j].getText());
                        grid[i][j].setText("");
                    }
                }
            }
        }
        if (canMove) {
            addRandom();
            checkLose();
        }
    }

    private void addRandom() {
        ArrayList<String> emptyCells = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (grid[i][j].getText().equals("")) {
                    emptyCells.add(i + "" + j);
                }
            }
        }
        int randIndex = new Random().nextInt(emptyCells.size());
        String cell = emptyCells.get(randIndex);
        int randNum = new Random().nextInt(10) < 9 ? 2 : 4;
        grid[Integer.parseInt(cell.substring(0, 1))][Integer.parseInt(cell.substring(1, 2))].setText(String.valueOf(randNum));
    }

    private void checkLose() {
        boolean isLose = true;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (grid[i][j].getText().equals("")) {
                    isLose = false;
                    break;
                }
                if (i < ROWS - 1 && Integer.parseInt(grid[i][j].getText()) == Integer.parseInt(grid[i + 1][j].getText())) {
                    isLose = false;
                    break;
                }
                if (j < COLS - 1 && Integer.parseInt(grid[i][j].getText()) == Integer.parseInt(grid[i][j + 1].getText())) {
                    isLose = false;
                    break;
                }
            }
            if (!isLose) {
                break;
            }
        }
        if (isLose) {
            System.out.println("You Lose!");
        }
    }
}
\end{code}

\begin{code}
private void moveUp() {
    boolean moved = false;
    for (int j = 0; j < COLS; j++) {
        for (int i = 1; i < ROWS; i++) {
            if (!grid[i][j].getText().equals("")) {
                int k;
                for (k = i - 1; k >= 0; k--) {
                    if (grid[k][j].getText().equals("")) {
                        break;
                    }
                    if (Integer.parseInt(grid[k][j].getText()) == Integer.parseInt(grid[i][j].getText())) {
                        int result = Integer.parseInt(grid[k][j].getText()) + Integer.parseInt(grid[i][j].getText());
                        grid[k][j].setText(String.valueOf(result));
                        grid[i][j].setText("");
                        moved = true;
                        break;
                    }
                }
                if (k != i - 1) {
                    grid[k + 1][j].setText(grid[i][j].getText());
                    grid[i][j].setText("");
                    moved = true;
                }
            }
        }
    }
    if (moved) {
        addRandomTile();
    }
}
\end{code}

\begin{code}
 <ion-item-sliding *ngFor="let item1 of List1">
    <ion-item>
      {{item1.name}}
    </ion-item>
 </ion-item-sliding>

<ion-item-sliding *ngFor="let item2 of List2">
 <ion-item>
    {{item2.name}}
 </ion-item>
</ion-item-sliding>
\end{code}


\begin{code}
import { Component } from '@angular/core';
import { YourService } from '../providers/your-service';

@Component({
 selector: 'app-home',
 templateUrl: 'home.page.html',
 styleUrls: ['home.page.scss'],
})
export class HomePage {

 List1: any[] = [];
 List2: any[] = [];

 constructor(private yourService: YourService) {
    this.yourService.getList1().subscribe(res => {
      this.List1 = res;
    });
 }

 fetchList2(id: number) {
    this.yourService.getList2(id).subscribe(res => {
      this.List2 = res;
    });
 }
}
\end{code}

In your `page.html`:

\begin{code}
<ion-item-sliding *ngFor="let item1 of List1">
 <ion-item (click)="fetchList2(item1.id)">
    {{item1.name}}
 </ion-item>
</ion-item-sliding>

<ion-item-sliding *ngFor="let item2 of List2">
 <ion-item>
    {{item2.name}}
 </ion-item>
</ion-item-sliding>
\end{code}

