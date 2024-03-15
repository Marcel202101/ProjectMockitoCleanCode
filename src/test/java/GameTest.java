import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    public static final int OPTION_ROCK = 0;
    public static final int OPTION_PAPER = 1;
    public static final int OPTION_SCISSORS = 2;

    private ByteArrayOutputStream out;
    @InjectMocks
    private Game game;

    @Mock
    Scanner scanner;
    @Mock
    Random random;

    @Before
    public void setup(){
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }
    @Test
    public void when_writeQuit_then_exitGame(){
        when(scanner.nextLine()).thenReturn("Quit");
        game.play();
        Assert.assertTrue(out.toString().contains("Let's play"));
    }

    @Test
    public void when_chooseRock_beatsScissors(){
        when(scanner.nextLine()).thenReturn("Rock").thenReturn("Quit");
        when(random.nextInt(3)).thenReturn(OPTION_SCISSORS);
        game.play();
        Assert.assertTrue(out.toString().contains("Computer chose scissors"));
        Assert.assertTrue(out.toString().contains("wins:1"));

    }
    @Test
    public void when_chooseScissors_beatsPaper(){
        when(scanner.nextLine()).thenReturn("Scissors").thenReturn("Quit");
        when(random.nextInt(3)).thenReturn(OPTION_PAPER);

        game.play();
        Assert.assertTrue(out.toString().contains("Computer chose paper"));
        Assert.assertTrue(out.toString().contains("wins:1"));

    }
    @Test
    public void when_choosePaper_beatsRocks(){
        when(scanner.nextLine()).thenReturn("Paper").thenReturn("Quit");
        when(random.nextInt(3)).thenReturn(OPTION_ROCK);
        game.play();
        Assert.assertTrue(out.toString().contains("Computer chose rock"));
        Assert.assertTrue(out.toString().contains("wins:1"));

    }
    @Test
    public void when_bothChooseRock_then_tie(){
        when(scanner.nextLine()).thenReturn("Rock").thenReturn("Quit");
        when(random.nextInt(3)).thenReturn(OPTION_ROCK);

        game.play();
        Assert.assertTrue(out.toString().contains("Computer chose rock"));
        Assert.assertTrue(out.toString().contains("ties:1"));

    }
    @Test
    public void when_bothChoosePaper_then_tie(){
        when(scanner.nextLine()).thenReturn("Paper").thenReturn("Quit");
        when(random.nextInt(3)).thenReturn(OPTION_PAPER);
        game.play();
        Assert.assertTrue(out.toString().contains("Computer chose paper"));
        Assert.assertTrue(out.toString().contains("ties:1"));

    }

    @Test
    public void when_bothChooseScissors_then_tie(){
        when(scanner.nextLine()).thenReturn("Scissors").thenReturn("Quit");
        when(random.nextInt(3)).thenReturn(OPTION_SCISSORS);
        game.play();
        Assert.assertTrue(out.toString().contains("Computer chose scissors"));
        Assert.assertTrue(out.toString().contains("ties:1"));

    }

    @Test
    public void when_ChooseScissorsAndComputerChooseRock_then_loses(){
        when(scanner.nextLine()).thenReturn("Scissors").thenReturn("Quit");
        when(random.nextInt(3)).thenReturn(OPTION_ROCK);
        game.play();
        Assert.assertTrue(out.toString().contains("Computer chose rock"));
        Assert.assertTrue(out.toString().contains("loses:1"));

    }
}
