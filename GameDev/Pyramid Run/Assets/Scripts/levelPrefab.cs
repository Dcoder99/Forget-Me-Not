using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class levelPrefab : MonoBehaviour
{

    // Use this for initialization
	enum prefabs {path40, path80, turn, t_junct, dead_end, coin};
	float p_len = 20f, p_len2 = 40f, t_len = 8.5f; // the values of half their lengths
	
	public static List<List<List<float>>> array = new List<List<List<float>>>();
	List<List<float>> level;	    // a grid reinitialized for each level
	List <float> prefab1; 	        // prefab1 = {which_prefab, x_coordinate, z_coordinate, y_rotation, index_of_prefab}
    void Start()
    {

		// LEVEL 0:
		level = new List<List<float>>();
        prefab1 = new List <float>() {(float)prefabs.path80, 0, p_len2, 0, -1f};
		level.Add(prefab1);
		prefab1 = new List <float>(){(float)prefabs.turn, 0, p_len2*2 + t_len, 0, -1f};
		level.Add(prefab1);
		prefab1 = new List <float>(){(float)prefabs.path80, p_len2 + t_len, p_len2*2 + t_len, 90, -1f};
		level.Add(prefab1);
		
		array.Add(level);

		// LEVEL 1:
		level = new List<List<float>>();
        prefab1 = new List <float>() {(float)prefabs.path80, 0, p_len2, 0, -1f};
		level.Add(prefab1);
		prefab1 = new List <float>(){(float)prefabs.turn, 0, p_len2*2 + t_len, 90, -1f};
		level.Add(prefab1);
		prefab1 = new List <float>(){(float)prefabs.path80, -(p_len2 + t_len), p_len2*2 + t_len, 90, -1f};
		level.Add(prefab1);
		prefab1 = new List <float>(){(float)prefabs.turn, -(2*p_len2 + 2*t_len), p_len2*2 + t_len, 270, -1f};
		level.Add(prefab1);
		prefab1 = new List <float>(){(float)prefabs.path40, -(2*p_len2 + 2*t_len), p_len2*2 + 2*t_len + p_len, 0, -1f};
		level.Add(prefab1);
		
		array.Add(level);

        //LEVEL 2:
        level = new List<List<float>>();
        prefab1 = new List<float>() { (float)prefabs.path80, 0, p_len2, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, 0, (p_len2 * 2 + t_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (t_len+p_len), (p_len2 * 2 + t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, 2*(t_len + p_len), (p_len2 * 2 + t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, 2*(t_len + p_len), (p_len2 + p_len), 0, -1f };
        level.Add(prefab1);

        array.Add(level);

        //	LEVEL 3:
        level = new List<List<float>>();
        prefab1 = new List<float>() { (float)prefabs.path80, 0, p_len2, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.t_junct, 0, p_len2 * 2 + t_len, 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path80, -(p_len2 + t_len), p_len2 * 2 + t_len, 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.dead_end, -(2 * p_len2 + t_len + 0.5f), p_len2 * 2 + t_len, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path80, p_len2 + t_len , p_len2 * 2 + t_len, 90, -1f };
        level.Add(prefab1);

        array.Add(level);

        //  LEVEL 4:
        level = new List<List<float>>();
        prefab1 = new List<float>() { (float)prefabs.path40, 0, p_len, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, 0, (p_len * 2 + t_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path80, (p_len2 + t_len), (p_len * 2 + t_len) , 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.t_junct, (2 * p_len2 + 2*t_len), (p_len * 2 + t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (p_len2*2 + t_len*2), (p_len * 3 + t_len*2), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (p_len2 * 2 + t_len * 2), (p_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, (p_len2 * 2 + t_len * 2), -(t_len), 270, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (p_len + p_len2 * 2 + t_len * 3), -(t_len), 90, -1f };
        level.Add(prefab1);

        array.Add(level);

        //  LEVEL 5:
        level = new List<List<float>>();
        prefab1 = new List<float>() { (float)prefabs.path40, 0, p_len, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.t_junct, 0, (p_len2 + t_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, -(p_len + t_len), (p_len2 + t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (p_len + t_len), (p_len2 + t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, -(p_len2 + 2 * t_len), (p_len2 + t_len), 270, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, (p_len2 + 2 * t_len), (p_len2 + t_len), 180, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, -(p_len2 + 2 * t_len), (3 * p_len + 2 * t_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (p_len2 + 2 * t_len), (3 * p_len + 2 * t_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, -(p_len2 + 2 * t_len), (p_len2 * 2 + 3 * t_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, (p_len2 + 2 * t_len), (p_len2 * 2 + 3 * t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, -(p_len + t_len), (p_len2 * 2 + 3 * t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, -(p_len + t_len), (p_len2 * 2 + 3 * t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (p_len + t_len), (p_len2 * 2 + 3 * t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.t_junct, 0, (p_len2 * 2 + 3 * t_len), 180, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, 0, (p_len2 * 2 + p_len + 4 * t_len), 0, -1f };
        level.Add(prefab1);

        array.Add(level);

        //LEVEL 6:

    }
}