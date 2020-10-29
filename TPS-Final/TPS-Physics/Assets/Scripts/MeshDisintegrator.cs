using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MeshDisintegrator : MonoBehaviour, IDamagable
{
    public int horizontalDivisions = 10;
    public int verticalDivisions = 5;
    public float forceFallOff = 1000; 
    public GameObject debrisObject;

    public float[] hPointsBottom;
    public float[] hPointsTop;
    public float[] vPointsLeft;
    public float[] vPointsRight;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
    }

    public void TakeDamage(Vector3 damageDirection, Vector3 pointOfImpact)
    {
        Disintegrate(damageDirection, pointOfImpact);
    }

    void Disintegrate(Vector3 damageDirection, Vector3 pointOfImpact)
    {
        GetComponent<Collider>().enabled = false;
        GetComponent<MeshRenderer>().enabled = false;


        float hDelta = (1.0f / horizontalDivisions);
        float vDelta = (1.0f / verticalDivisions);

        hPointsBottom = new float[horizontalDivisions + 1];
        hPointsTop = new float[horizontalDivisions + 1];
        vPointsLeft = new float[verticalDivisions + 1];
        vPointsRight = new float[verticalDivisions + 1];

        hPointsBottom[0] = 0;
        hPointsTop[0] = 0;
        vPointsLeft[0] = 0;
        vPointsRight[0] = 0;

        hPointsBottom[horizontalDivisions] = transform.localScale.x;
        hPointsTop[horizontalDivisions] = transform.localScale.x;
        vPointsLeft[verticalDivisions] = transform.localScale.y;
        vPointsRight[verticalDivisions] = transform.localScale.y;

        for (int i = 1; i < horizontalDivisions; i++)
        {
            hPointsBottom[i] = (hDelta * i + (i % 2 == 0 ? 1 : -1) * Random.Range(0.1f, 0.5f) * hDelta) * transform.localScale.x;
            hPointsTop[i] = (hDelta * i - (i % 2 == 0 ? 1 : -1) * Random.Range(0.1f, 0.5f) * hDelta) * transform.localScale.x;
        }

        for (int i = 1; i < verticalDivisions; i++)
        {
            vPointsLeft[i] = (vDelta * i - (i % 2 == 0 ? 1 : -1) * Random.Range(0.1f, 0.5f) * hDelta) * transform.localScale.y;
            vPointsRight[i] = (vDelta * i + (i % 2 == 0 ? 1 : -1) * Random.Range(0.1f, 0.5f) * hDelta) * transform.localScale.y;
        }

        GameObject cube = null;
        List<GameObject> cubes = new List<GameObject>();

        for (int i = 0; i < horizontalDivisions; i++)
        {
            for (int j = 0; j < verticalDivisions; j++)
            {
                Vector3 relPos = transform.localScale.x * hDelta * (i + 0.5f) * transform.right 
                    + transform.localScale.y * vDelta * (j + 0.5f) * transform.up;
                Vector3 center = transform.localScale.x * 0.5f * transform.right
                    + transform.localScale.y * 0.5f * transform.up;
                Vector3 pos = relPos - center + transform.position;

                GameObject prev = cube;
                cube = Instantiate(debrisObject, pos, transform.rotation);
                cubes.Add(cube);

                cube.transform.localScale = new Vector3(1, 1, transform.localScale.z);
                Vector3 size = cube.GetComponent<BoxCollider>().size * 0.8f;
                cube.GetComponent<BoxCollider>().size = new Vector3(size.x * transform.localScale.x / horizontalDivisions, size.y * transform.localScale.y / verticalDivisions, size.z * transform.localScale.z);
                Debris deb = cube.GetComponent<Debris>();

                deb.points = new Vector2[8];
                Vector2 relPosNorm = new Vector2(transform.localScale.x * hDelta * (i + 0.5f), transform.localScale.y * vDelta * (j + 0.5f));
                deb.points[0] = new Vector2(hPointsBottom[i], 0) - relPosNorm;
                deb.points[1] = new Vector2(hPointsBottom[i + 1], 0) - relPosNorm;
                deb.points[2] = new Vector2(transform.localScale.x, vPointsRight[j]) - relPosNorm;
                deb.points[3] = new Vector2(transform.localScale.x, vPointsRight[j + 1]) - relPosNorm;
                deb.points[4] = new Vector2(hPointsTop[i + 1], transform.localScale.y) - relPosNorm;
                deb.points[5] = new Vector2(hPointsTop[i], transform.localScale.y) - relPosNorm;
                deb.points[6] = new Vector2(0, vPointsLeft[j + 1]) - relPosNorm;
                deb.points[7] = new Vector2(0, vPointsLeft[j]) - relPosNorm;
                deb.SetVertices();

                if (prev != null && j != 0 && Random.Range(0, 10) > 1)
                {
                    if (Random.Range(0, 10) >= 5)
                    {
                        Destroy(cube.GetComponent<Rigidbody>());
                        cube.transform.parent = prev.transform;
                    }
                    else
                    {
                        int index = cubes.Count - 1 - verticalDivisions;
                        if (index >= 0)
                        {
                            Destroy(cube.GetComponent<Rigidbody>());
                            cube.transform.parent = cubes[index].transform;
                        }
                    }
                }
            }
        }

        foreach (GameObject piece in cubes)
        {
            if (piece.GetComponent<Rigidbody>() != null)
            {
                piece.GetComponent<Rigidbody>().isKinematic = false;
                piece.GetComponent<Rigidbody>().AddForce(
                    damageDirection.normalized * 
                    forceFallOff / Mathf.Pow(Vector3.Distance(pointOfImpact, piece.transform.position), 3));
            }
        }
    }
}
