using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class GameScript : MonoBehaviour
{

    public GameObject maxPrefab, path40Prefab, path80Prefab, turnPrefab, tPrefab, deadendPrefab, coin;
    public static int maxCoinIdx = -1, targetIdx = 1;

    // GameObject getPrefab(int idx)
    // {
    // 	// enum prefabs {path40, path80, turn, t_junct, dead_end, coin};
    // 	switch(idx)
    // 	{
    // 		case 0:
    // 			return max;
    // 		case 1:
    // 			return path40Prefab;
    //      case 2;
    //          return path80Prefab;
    // 		case 3:
    // 			return turnPrefab;
    // 		case 4:
    // 			return tPrefab;
    //      case 5:
    //          return deadendPrefab;
    // 		default:
    // 			Debug.Log("Prefab index Out of Bounds");
    // 			return path40Prefab; // Default prefab
    // 	}
    // }

    void generateLevel(int currentLevel)
    {
        Debug.Log("Generating Level..." + currentLevel);
        List<List<float>> level = levelPrefab.array[currentLevel];

        // prefab1 = {which_prefab, x_coordinate, z_coordinate, y_rotation, index_of_prefab}
        foreach (List<float> prefab1 in level)
        {
            if (prefab1[4] == -1f)
            {
                Debug.Log("Instantiate prefab");

                //Instantiating Path40
                if ((int)prefab1[0] == 0)
                {
                    Instantiate(path40Prefab, new Vector3(prefab1[1], 0, prefab1[2]), transform.rotation * Quaternion.Euler(0, prefab1[3], 0));
                }

                //Instantiating Path80
                else if ((int)prefab1[0] == 1)
                {
                    Instantiate(path80Prefab, new Vector3(prefab1[1], 0, prefab1[2]), transform.rotation * Quaternion.Euler(0, prefab1[3], 0));
                }

                //Instantiating Turn 
                else if ((int)prefab1[0] == 2)
                {
                    Instantiate(turnPrefab, new Vector3(prefab1[1], 0, prefab1[2]), transform.rotation * Quaternion.Euler(0, prefab1[3], 0));
                }

                //Instantiating T Junction
                else if ((int)prefab1[0] == 3)
                {
                    Instantiate(tPrefab, new Vector3(prefab1[1], 0, prefab1[2]), transform.rotation * Quaternion.Euler(0, prefab1[3], 0));
                }

                //Instantiating Dead End
                else if ((int)prefab1[0] == 4)
                {
                    Instantiate(deadendPrefab, new Vector3(prefab1[1], 0, prefab1[2]), transform.rotation * Quaternion.Euler(0, prefab1[3], 0));
                }
            }
            else
            {
                Debug.Log("Generating Coins!");
                // It's a coin!
                string coinName = prefab1[4].ToString();
                GameObject coinObject = Instantiate(coin, new Vector3(prefab1[1], 0, prefab1[2]), transform.rotation * Quaternion.Euler(0, prefab1[3], 0));
                coinObject.name = coinName;
                if (prefab1[4] > maxCoinIdx)
                {
                    maxCoinIdx = (int)prefab1[4];
                }
            }
        }
    }

    void Init()
    {
        Debug.Log("INIT");
        maxCoinIdx = -1;
        targetIdx = 1;

        //Instantiating Max
        //GameObject maxInstance =  Instantiate(maxPrefab, new Vector3(0, 0, 4), transform.rotation * Quaternion.Euler(0, 0, 0));
        //maxInstance.AddComponent<Collisions>();

        

    }
    void Start()
    {
        Init();
        generateLevel(PlayerPrefs.GetInt("currentLevel", 3));

        Debug.Log(PlayerPrefs.GetInt("currentLevel"));
    }


}
