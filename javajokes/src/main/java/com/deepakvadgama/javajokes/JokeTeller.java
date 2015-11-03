package com.deepakvadgama.javajokes;

import java.util.Random;

public class JokeTeller {

    private static Random random = new Random();
    private static String[] jokeList = {
            "My Dad used to say 'always fight fire with fire', which is probably why he got thrown out of the fire brigade",
            "I'm on a whiskey diet. I've lost three days already.",
            "Start every day off with a smile and get it over with.",
            "My wife sent her photograph to the Lonely Hearts Club. They sent it back saying they weren't that lonely.",
            "A snail walks into a bar and the barman tells him there's a strict policy about having snails in the bar and so kicks him out. A year later the same snail re-enters the bar and asks the barman \"What did you do that for?\"",
            "A: Hey, man! Please call me a taxi. \nB: Yes, sir. You are a taxi. ",
            "\"I was born in California.\" \n\"Which part?\" \n\"All of me.\"",
            "Teacher: Did your father help your with your homework? \nStudent: No, he did it all by himself."
    };

    public String getJoke(){
        return jokeList[random.nextInt(jokeList.length)];
    }

}
