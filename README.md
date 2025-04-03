# KMeans
Kmeans example from apache commons. https://commons.apache.org/proper/commons-math/userguide/ml.html

Used for 
- Clustering: locations into k clusters
- Sampling: obtain k points in total, one from each cluster with the shortest distance to the cluster's centroid

## Install in Maven project
For a Maven repository, use version 3.6.1 
https://mvnrepository.com/artifact/org.apache.commons/commons-math3/3.6.1

```
<!-- https://mvnrepository.com/artifact/org.apache.commons/commons-math3 -->
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-math3</artifactId>
    <version>3.6.1</version>
</dependency>
```

Note: I did not use version 4 as couldn't import ml library "org.apache.commons.math4.ml*"



## Example:
Clustering into 3 clusters:
![image](https://github.com/user-attachments/assets/f2891b81-bdb1-450f-8a34-2ba9e42864eb)

 
Sampling 3 points:
![image](https://github.com/user-attachments/assets/2df77dd5-34de-4caa-84e9-e6fb6ea47bd1)
