using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameScript : MonoBehaviour
{

    public GameObject path40Prefab, path80Prefab, turnPrefab, tPrefab;

    // GameObject getPrefab(int idx)
    // {
	// 	// enum prefabs {path40, path80, turn, t_junct, coin};
	// 	switch(idx)
	// 	{
	// 		case 0:
	// 			return path40Prefab;
	// 		case 1:
	// 			return path80Prefab;
	// 		case 3:
	// 			return turnPrefab;
	// 		case 4:
	// 			return tPrefab;
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
			if(prefab1[4] == -1f)
			{
				Debug.Log("Instantiate prefab");
				if((int)prefab1[0] == 0){
					Instantiate(path40Prefab, new Vector3(prefab1[1], 0, prefab1[2]), transform.rotation * Quaternion.Euler(0, prefab1[3], 0));
				}
				if((int)prefab1[0] == 1){
					Instantiate(path80Prefab, new Vector3(prefab1[1], 0, prefab1[2]), transform.rotation * Quaternion.Euler(0, prefab1[3], 0));
				}
				if((int)prefab1[0] == 2){
					Instantiate(turnPrefab, new Vector3(prefab1[1], 0, prefab1[2]), transform.rotation * Quaternion.Euler(0, prefab1[3], 0));
				}
				if((int)prefab1[0] == 3){
					Instantiate(tPrefab, new Vector3(prefab1[1], 0, prefab1[2]), transform.rotation * Quaternion.Euler(0, prefab1[3], 0));
				}
			}
		}
    }
    void Start()
    {
        generateLevel(PlayerPrefs.GetInt("currentLevel", 0));
    }

    // Update is called once per frame
    void Update()
    {

    }
}
