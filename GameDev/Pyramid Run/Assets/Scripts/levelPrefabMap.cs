using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class levelPrefabMap : MonoBehaviour
{

    // Use this for initialization
    enum prefabs { path40, path80, turn, t_junct, dead_end, coin };
    float p_len = 20f, p_len2 = 40f, t_len = 8.5f; // the values of half their lengths

    public static List<List<List<float>>> array = new List<List<List<float>>>();
    List<List<float>> level;        // a grid reinitialized for each level
    List<float> prefab1; 	        // prefab1 = {which_prefab, x_coordinate, z_coordinate, y_rotation, index_of_prefab}
    void Start()
    {

        // LEVEL 0T:
        level = new List<List<float>>();
        prefab1 = new List<float>() { (float)prefabs.path80, 0, p_len2, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, 0, p_len2 * 2 + t_len, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path80, p_len2 + t_len, p_len2 * 2 + t_len, 90, -1f };
        level.Add(prefab1);
        array.Add(level);


        // LEVEL 1: B
        level = new List<List<float>>();
        prefab1 = new List<float>() { (float)prefabs.path80, 0, p_len2, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, 0, p_len2 * 2 + t_len, 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.coin, 0, p_len2 * 2 + t_len, 90, 1 };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path80, -(p_len2 + t_len), p_len2 * 2 + t_len, 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, -(2 * p_len2 + 2 * t_len), p_len2 * 2 + t_len, 270, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.coin, -(2 * p_len2 + 2 * t_len), p_len2 * 2 + t_len, 270, 2 };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, -(2 * p_len2 + 2 * t_len), p_len2 * 2 + 2 * t_len + p_len, 0, -1f };
        level.Add(prefab1);
        array.Add(level);


        //LEVEL 2C:
        level = new List<List<float>>();
        prefab1 = new List<float>() { (float)prefabs.path80, 0, p_len2, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, 0, (p_len2 * 2 + t_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (t_len + p_len), (p_len2 * 2 + t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, 2 * (t_len + p_len), (p_len2 * 2 + t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, 2 * (t_len + p_len), (p_len2 + p_len), 0, -1f };
        level.Add(prefab1);
        array.Add(level);


        //	LEVEL 3D:
        level = new List<List<float>>();
        prefab1 = new List<float>() { (float)prefabs.path80, 0, p_len2, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.t_junct, 0, p_len2 * 2 + t_len, 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path80, -(p_len2 + t_len), p_len2 * 2 + t_len, 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.dead_end, -(2 * p_len2 + t_len + 0.5f), p_len2 * 2 + t_len, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path80, p_len2 + t_len, p_len2 * 2 + t_len, 90, -1f };
        level.Add(prefab1);
        array.Add(level);


        //  LEVEL 4E:
        level = new List<List<float>>();
        prefab1 = new List<float>() { (float)prefabs.path40, 0, p_len, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, 0, (p_len * 2 + t_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path80, (p_len2 + t_len), (p_len * 2 + t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.t_junct, (2 * p_len2 + 2 * t_len), (p_len * 2 + t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (p_len2 * 2 + t_len * 2), (p_len * 3 + t_len * 2), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (p_len2 * 2 + t_len * 2), (p_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, (p_len2 * 2 + t_len * 2), -(t_len), 270, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (p_len + p_len2 * 2 + t_len * 3), -(t_len), 90, -1f };
        level.Add(prefab1);
        array.Add(level);


        //  LEVEL 5A:
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


        //LEVEL 6F:
        level = new List<List<float>>();
        prefab1 = new List<float>() { (float)prefabs.path40, 0, p_len, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, 0, (3 * p_len + 2 * t_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.t_junct, 0, (p_len2 + t_len), 270, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (p_len + t_len), (p_len2 + t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, (p_len2 + 2 * t_len), (p_len2 + t_len), 180, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (p_len2 + 2 * t_len), (3 * p_len + 2 * t_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.t_junct, (p_len2 + 2 * t_len), (p_len2 * 2 + 3 * t_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (p_len + t_len), (p_len2 * 2 + 3 * t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, 0, (p_len2 * 2 + 3 * t_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (3 * t_len + 3 * p_len), 4 * p_len + 3 * t_len, 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, (4 * t_len + 4 * p_len), 4 * p_len + 3 * t_len, 180, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (4 * t_len + 4 * p_len), 5 * p_len + 4 * t_len, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.t_junct, (4 * t_len + 4 * p_len), 6 * p_len + 5 * t_len, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (3 * t_len + 3 * p_len), 6 * p_len + 5 * t_len, 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.dead_end, (3 * t_len + 2 * p_len), 6 * p_len + 5 * t_len, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (5 * t_len + 5 * p_len), 6 * p_len + 5 * t_len, 90, -1f };
        level.Add(prefab1);
        array.Add(level);


        //LEVEL 7G:
        level = new List<List<float>>();
        prefab1 = new List<float>() { (float)prefabs.path40, 0, p_len, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, 0, (3 * p_len + 2 * t_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.t_junct, 0, (p_len2 + t_len), 270, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (p_len + t_len), (p_len2 + t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.dead_end, (2 * p_len + t_len), (p_len2 + t_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, 0, (5 * p_len + 4 * t_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, -(p_len + t_len), (p_len2 * 2 + 3 * t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.dead_end, -(2 * p_len + t_len), (p_len2 * 2 + 3 * t_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.t_junct, 0, (p_len2 * 2 + 3 * t_len), 90, -1f };
        level.Add(prefab1);
        array.Add(level);


        // LEVEL 8H:
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
        prefab1 = new List<float>() { (float)prefabs.t_junct, -(p_len2 + 2 * t_len), (4 * p_len + 3 * t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, -(p_len2 + 5f * t_len), (4 * p_len + 3 * t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.t_junct, (p_len2 + 2f * t_len), (4 * p_len + 3 * t_len), -90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.dead_end, (p_len2 * 2 + 2.5f * t_len + 1), (4 * p_len + 3 * t_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (p_len2 + 5f * t_len), (4 * p_len + 3 * t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.dead_end, -(p_len2 * 2 + 2.5f * t_len + 1), (4 * p_len + 3 * t_len), 0, -1f };
        level.Add(prefab1);
        array.Add(level);


        // LEVEL 9I:
        level = new List<List<float>>();
        prefab1 = new List<float>() { (float)prefabs.path40, 0, p_len, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, 0, (3 * t_len + p_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, t_len * 3, (p_len + 3 * t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, (p_len2 + 1.5f * t_len), (p_len + 3 * t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (p_len2 + 1.5f * t_len), 2 * t_len, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, (p_len2 + 1.5f * t_len), -t_len, -90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (p_len2 + 4.5f * t_len), (-t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, (2 * p_len2 + 3f * t_len), (-t_len), 180, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path80, (2 * p_len2 + 3f * t_len), (p_len2), 180, -1f };
        level.Add(prefab1);
        array.Add(level);


        //LEVEL 10K:
        level = new List<List<float>>();
        prefab1 = new List<float>() { (float)prefabs.path80, 0, p_len2, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, 0, (p_len2 * 2 + t_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path80, (t_len + p_len2), (p_len2 * 2 + t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, 2 * (t_len + p_len2), (p_len2 * 2 + t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path80, 2 * (t_len + p_len2), p_len2, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, 2 * (t_len + p_len2), -(t_len), 180, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path80, (t_len + p_len2), -(t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, 0, -(t_len), 270, -1f };
        level.Add(prefab1);
        array.Add(level);


        //LEVEL 11J:
        level = new List<List<float>>();
        prefab1 = new List<float>() { (float)prefabs.path80, 0, p_len2, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, 0, p_len2 * 2 + t_len, 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path80, -(p_len2 + t_len), (2 * p_len2 + t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.t_junct, -(2 * p_len2 + 2 * t_len), (2 * p_len2 + t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, -2 * (p_len2 + t_len), (2 * p_len2 - p_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, -2 * (p_len2 + t_len), (2 * p_len2 - 2 * p_len - t_len), 270, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, -(2 * p_len2 + 3 * t_len + p_len), (2 * p_len2 - 2 * p_len - t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.dead_end, -(2 * p_len2 + 3 * t_len + 2 * p_len + 0.5f), (2 * p_len2 - 2 * p_len - t_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, -2 * (p_len2 + t_len), (2 * p_len2 + p_len + 2 * t_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.turn, -2 * (p_len2 + t_len), (2 * p_len2 + 2 * p_len + 3 * t_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path80, -(p_len2 + t_len), (2 * p_len2 + 2 * p_len + 3 * t_len), 90, -1f };
        level.Add(prefab1);
        array.Add(level);


        //LEVEL 12L:
        level = new List<List<float>>();
        prefab1 = new List<float>() { (float)prefabs.path40, 0, p_len, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.t_junct, 0, p_len * 2 + t_len, 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, -(p_len + t_len), p_len * 2 + t_len, 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (p_len + t_len), (p_len * 2 + t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.t_junct, (2 * p_len + 2 * t_len), (p_len * 2 + t_len), 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.t_junct, -(2 * p_len + 2 * t_len), (p_len * 2 + t_len), 270, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (2 * p_len + 2 * t_len), (p_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, -(2 * p_len + 2 * t_len), (p_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.dead_end, (2 * p_len + 2 * t_len), 0, 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.dead_end, -(2 * p_len + 2 * t_len), 0, 90, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, (2 * p_len + 2 * t_len), (3 * p_len + 2 * t_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.path40, -(2 * p_len + 2 * t_len), (3 * p_len + 2 * t_len), 0, -1f };
        level.Add(prefab1);
        prefab1 = new List<float>() { (float)prefabs.dead_end, (2 * p_len + 2 * t_len), (4 * p_len + 2 * t_len), 90, -1f };
        level.Add(prefab1);
        array.Add(level);
    }
}