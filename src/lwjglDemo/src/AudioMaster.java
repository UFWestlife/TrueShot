import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;
import java.util.ArrayList;
import java.util.List;


public class AudioMaster {

    private static List<Integer> buffers = new ArrayList<>();

    public static void init(){
        try {
            AL.create();
        } catch (LWJGLException e){
            e.printStackTrace();
        }
    }


    public static void setListenerData(){
        AL10.alListener3f(AL10.AL_POSITION, 0, 0, 0);
        AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);

    }

    public static int loadSound(String file){
        int buffer = AL10.alGenBuffers();
        buffers.add(buffer);
        WaveData waveFile = WaveData.create(file);
        AL10.alBufferData(buffer, waveFile.format, waveFile.data, waveFile.samplerate);

        waveFile.dispose();
        return buffer;

    }

    public static void cleanUp(){
        for (int buffer : buffers){
            AL10.alDeleteBuffers(buffer);
        }
        AL.destroy();
    }

}
