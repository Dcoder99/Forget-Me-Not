

//-----------------------------------------------------------LEVEL 3-------------------------------------------------------------//


using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class generateLevel : MonoBehaviour {

    //Referring To Prefabs
    public GameObject path80;
    public GameObject path40;
    public GameObject turn;
    public GameObject T_junc;
    public GameObject Max;


    //Contants
    float p_len = 20f;
    float p_len2 = 40f;
    float t_len = 8.5f;
    
    void Start () {

        Vector3 pos = new Vector3(0, 0, 0);
        Debug.Log("pos.x: " + pos.x + "pos.y: " + pos.y + "pos.z: " + pos.z);

        //Instantiating Max
        Instantiate(Max, new Vector3(pos.x,pos.y,pos.z), transform.rotation * Quaternion.Euler(0, 0, 0));
        

        Instantiate(path80, new Vector3(0,0, p_len2), transform.rotation * Quaternion.Euler(0, 0, 0));
        Instantiate(T_junc, new Vector3(0,0, (2 * p_len2 + t_len)), transform.rotation * Quaternion.Euler(0, 90, 0));
        Instantiate(path40, new Vector3(-(t_len+p_len), 0 , (2 * p_len2 + t_len)), transform.rotation * Quaternion.Euler(0, 90, 0));
        //Instantiate(path40, new Vector3((t_len + p_len), 0 , ), transform.rotation * Quaternion.Euler(0, 90, 0));
    }

    void Update () {
		
	}
}