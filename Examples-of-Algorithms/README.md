#### 1) Eliminate duplicate files in two directories. 

A Question:

There are many video in a laptop which I bought over ten years ago. Although I have copied some of them occasionally to my desktop computer, I downloaded new video when I was using the old laptop and I also added new video to my desktop. The size of the file is as large as about 50 GB and there are hundreds of them. Months ago my younger watered this old one so that I expose it under the scorching sun to make it dry. Fortunately, it was recovered, therefore, I have been using it until now. 

In case of that it will break down, I'd better copy the files to my cloud and desktop. It is effortless to just copy all the file to new directory and the duplicated ones will be overridden. Whereas, they are far too large so that it is time-consuming to copy all of them. When I was trying to find an efficient approach to tackle this problem, all of sudden, as a programmer, I could adopt algorithms. It is a chance to practise. 

There are two solutions. The first is simple but the cost of time is significantly high while the second is much better and it is even better when there are substantial number of files. 

The first one, which is the simplest one, is to iterate all the files in each directory and to compare them. Its complexity of time is $O(n_2)$. Apparently, I won't use it because the time will increase dramatically as the number of files increases. 

The second one is to use a binary tree to store the files of one directory, which we suppose it to M, and an array list to store the other, N. When the program find a duplicated file in the binary tree, it will delete the file in the list. Seldom had I rename these files, it is implausible to remove what I want to keep.

