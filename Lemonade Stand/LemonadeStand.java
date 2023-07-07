import java.util.Random;
public class LemonadeStand 
{
    private static int aliceSum = 0; 
    private static int bobSum = 0;
    private static int candySum = 0;

    public static int calculateUtility(int playerPosition, int[] neighbors) 
    {
        int clockwiseDistance = Integer.MAX_VALUE;;
        int counterclockwiseDistance = Integer.MAX_VALUE;;
        int utility = 0;
        // Calculate clockwise and counterclockwise distances

        if (playerPosition == neighbors[0] && neighbors[0] == neighbors[1])
        {
            return 8;
        }
        else if(neighbors[0] == neighbors[1])
        {
            return 12;
        }
        else if(playerPosition == neighbors[0])
        {
            return 6;
        }
        else if(playerPosition == neighbors[1])
        {
            return 6;
        }
        int distance =0;
        for(int i=0; i<neighbors.length;i++)
        {
            distance = (neighbors[i] - playerPosition + 12) % 12;
            clockwiseDistance = Math.min(clockwiseDistance, distance);
            counterclockwiseDistance = Math.min(counterclockwiseDistance, 12 - distance);
        }
        utility = clockwiseDistance + counterclockwiseDistance;
        return utility;
    }

    public void aliceUtilitySum(int player, int[]neighbor)
    {
        //System.out.println("Alice's Utility with Random strategy: " + calculateUtility(player, neighbor));
        aliceSum = aliceSum + calculateUtility(player, neighbor);
    }

    public void bobUtilitySum(int player, int[]neighbor)
    {
        //System.out.println("Bob's Utility with Random strategy: " + calculateUtility(player, neighbor));
        bobSum = bobSum + calculateUtility(player, neighbor);
    }

    public void candyUtilitySum(int player, int[]neighbor)
    {
        //System.out.println("Candy's Utility with Random strategy: " + calculateUtility(player, neighbor));
        candySum = candySum + calculateUtility(player, neighbor);
    }

    public int Addhours(int currentPos, int hours)          // returns the position of a player after x number of hours
    {
        int newHour = (currentPos + hours) % 12;
        if (newHour == 0) {
            newHour = 12;
        }
        return newHour;
    }

    public int positionGen()            // generates a position between 1 and 12
    {
        Random r = new Random();
        int low = 1;
        int high = 13;
        return r.nextInt(high-low) + low;
    }

    public void EquidistandStrategy()
    {
        int alicePosition;
        int bobPosition;
        int candyPosition ;

        for(int i =0; i<25; i++)           // responsible for calculating through a 100 iterations for Equidistant Strategy
        {   
            alicePosition = positionGen();
            bobPosition = Addhours(alicePosition, 4);
            candyPosition = Addhours(bobPosition, 4);

            alicePosition = positionGen();
            bobPosition = alicePosition;
            candyPosition = alicePosition;

            int aliceNeighbors[] = { bobPosition, candyPosition };
            int bobNeighbors[] = { alicePosition, candyPosition };
            int candyNeighbors[] = { alicePosition, bobPosition };

            aliceUtilitySum(alicePosition, aliceNeighbors);
            bobUtilitySum(bobPosition, bobNeighbors);
            candyUtilitySum(candyPosition, candyNeighbors);
            
        }

        System.out.println("Alice's Utility with Equidistant strategy: " + aliceSum);
        System.out.println("Bob's Utility with Equidistant strategy: " + bobSum);
        System.out.println("Candy's Utility with Equidistant strategy: " + candySum);
    }

    public void randomStrategy()
    {
        int alicePosition;
        int bobPosition;
        int candyPosition;

        for(int i =0; i<25; i++)           // responsible for calculating through a 100 iterations for Random Strategy
        {   
            alicePosition = positionGen();
            bobPosition = positionGen();
            candyPosition = positionGen();

            int aliceNeighbors[] = { bobPosition, candyPosition };
            int bobNeighbors[] = { alicePosition, candyPosition };
            int candyNeighbors[] = { alicePosition, bobPosition };

            aliceUtilitySum(alicePosition, aliceNeighbors);
            bobUtilitySum(bobPosition, bobNeighbors);
            candyUtilitySum(candyPosition, candyNeighbors);
            
        }

        System.out.println("Alice's Utility with Random strategy: " + aliceSum);
        System.out.println("Bob's Utility with Random strategy: " + bobSum);
        System.out.println("Candy's Utility with Random strategy: " + candySum);
    }

    public void oppositeSideStrategy()
    {
        int alicePosition;
        int bobPosition;
        int candyPosition;
        int distance; 

        for(int i =0; i<25; i++)       
        {   
            alicePosition = positionGen();
            bobPosition = Addhours(alicePosition, 6);
            candyPosition = positionGen();

            int aliceNeighbors[] = { bobPosition, candyPosition };
            int bobNeighbors[] = { alicePosition, candyPosition };
            int candyNeighbors[] = { alicePosition, bobPosition };

            aliceUtilitySum(alicePosition, aliceNeighbors);
            bobUtilitySum(bobPosition, bobNeighbors);
            candyUtilitySum(candyPosition, candyNeighbors);
            
        }

        System.out.println("Alice's Utility with Opposite Side strategy: " + aliceSum);
        System.out.println("Bob's Utility with Opposite Side strategy: " + bobSum);
        System.out.println("Candy's Utility with Opposite Side strategy: " + candySum);
    }

    public void sacrificeStrategy()
    {
        int alicePosition;
        int bobPosition;
        int candyPosition;
        int distance; 

        for(int i =0; i<25; i++)       
        {   
            alicePosition = positionGen();
            bobPosition = Addhours(alicePosition, 1);
            candyPosition = Addhours(alicePosition, -1);

            int aliceNeighbors[] = { bobPosition, candyPosition };
            int bobNeighbors[] = { alicePosition, candyPosition };
            int candyNeighbors[] = { alicePosition, bobPosition };

            aliceUtilitySum(alicePosition, aliceNeighbors);
            bobUtilitySum(bobPosition, bobNeighbors);
            candyUtilitySum(candyPosition, candyNeighbors);

        }
        System.out.println("Alice's Utility with Sacrifice Strategy: " + aliceSum);
        System.out.println("Bob's Utility with Sacrifice Strategy: " + bobSum);
        System.out.println("Candy's Utility with Sacrifice Strategy: " + candySum);
    }

    public static void main(String[] args) 
    {
        LemonadeStand obj = new LemonadeStand();
        obj.randomStrategy();
        obj.EquidistandStrategy();
        obj.oppositeSideStrategy();
        obj.sacrificeStrategy();
    }
}