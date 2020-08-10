package Game.Map;

import Engine.GameFrame;

import java.io.*;
import java.util.ArrayList;

/**
 * This is a map
 * @author Ahmad Foroughi
 * @version 1.0
 */
public class Map implements Serializable
{
    //----------------------------------Fields----------------------------------

    //SoftWalls
    private ArrayList<SoftWall> softWalls;
    //HardWalls
    private ArrayList<HardWall> hardWalls;
    //File Name  (Map's Address)
    private final String fileName;
    //Row
    private int row;
    //Column
    private int column;
    //map Array
    private int[][] map;
    //Each Wall Width
    private int eachWallWidth;
    //Each Wall Height
    private int eachWallHeight;
    //List of Proper Locations to put ability or tank
    private ArrayList<Integer> properXLocations;
    private ArrayList<Integer> properYLocations;

    /**
     * Create a new Map
     */
    public Map(String mapAddress)
    {
        softWalls = new ArrayList<>();
        hardWalls = new ArrayList<>();
        properXLocations = new ArrayList<>();
        properYLocations = new ArrayList<>();
        this.fileName = mapAddress;
        try
        {
            row = getNumberOfLines(fileName);
            column = getNumberOfColumns(fileName);
            map = convertToArray(row,column);
            eachWallWidth = GameFrame.GAME_WIDTH / column;
            eachWallHeight = GameFrame.GAME_HEIGHT / row;

            createMap();

        }
        catch (Exception ex)
        {
            System.exit(-1);
        }
    }

    /**
     * @return ArrayList<SoftWall>
     */
    public ArrayList<SoftWall> getSoftWalls() {
        return softWalls;
    }


    /**
     * @return ArrayList<HardWall>
     */
    public ArrayList<HardWall> getHardWalls() {
        return hardWalls;
    }



    /**
     * Get number of lines in file using regex
     * @param fileName is file name
     * @return number of lines (\n)
     * @throws Exception is any IO exception
     */
    public static int getNumberOfLines(String fileName) throws Exception
    {
        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fis.read(bytes);
        String data = new String(bytes);
        String[] strings = data.split("\n");
        return strings.length;
    }


    /**
     * Get Number of Columns
     * @param fileName is fileName
     * @return integer
     * @throws Exception is any sort of exception that relates to file
     */
    public static int getNumberOfColumns(String fileName) throws Exception
    {
        FileReader fr=new FileReader(fileName);   //reads the file
        BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
        String line = br.readLine();
        char[] lineToArray = line.toCharArray();
        return lineToArray.length;
    }

    /**
     * Convert File Array To int[][] array
     * @param row is row
     * @param column is column
     * @return array
     * @throws IOException is any sort of io exception
     */
    public int[][] convertToArray(int row, int column) throws IOException {
        int[][] temp = new int[row][column];
        FileReader fr=new FileReader(fileName);   //reads the file
        BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
        String line;
        int i =0;
        while ((line= br.readLine()) !=null)
        {
            char[] lineToArray = line.toCharArray();
            for(int j=0; j<column; j++)
            {
                temp[i][j] = lineToArray[j] - '0';
            }
            i++;
        }
        return temp;
    }


    /**
     * @return String
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @return Integer
     */
    public int getEachWallWidth() {
        return eachWallWidth;
    }

    /**
     * @return Integer
     */
    public int getEachWallHeight() {
        return eachWallHeight;
    }

    /**
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> getProperXLocations() {
        return properXLocations;
    }

    /**
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> getProperYLocations() {
        return properYLocations;
    }

    /**
     * Reset Map To Default
     */
    public void resetMap()
    {
        softWalls.clear();
        hardWalls.clear();
        createMap();
    }

    /**
     * Create Map
     */
    public void createMap()
    {
        int xi = 0;
        int yi = 0;

        for(int i=0; i<row; i++)
        {
            xi = 0;
            for(int j=0 ; j<column;j++)
            {
                if(map[i][j] == 0)
                {
                    //TODO : FIX THIS
                    //Normal
                    properXLocations.add(xi);
                    properYLocations.add(yi);
                }
                else if(map[i][j] == 1)
                {
                    //HardWall
                    HardWall hardWall = new HardWall(xi,yi);
                    hardWalls.add(hardWall);
                }
                else if(map[i][j] == 2)
                {
                    //SoftWall
                    SoftWall softWall = new SoftWall(xi,yi);
                    softWalls.add(softWall);
                }

                xi += eachWallWidth;
            }
            yi += eachWallHeight;
        }
    }
}
