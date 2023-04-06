# DOWME
UNIST 2023S1 CSE36401 Software Engineering Group 10

20171260 JunGu Han 한준구   
20181014 YoungJin Kwon 권영진

***

Part III Done

***

## SSH Authorization before running "run.sh"
In accessing to Github URL, we are using SSH URL (git@github.com:KAN-RYU/DOWME.git).
So when you start a Docker image created from the Dockerfile and run.sh, you will need a SSH key to get access to Git.


Before running 'sh run.sh', please create and authorize the SSH Key of the Docker image, by doing the following.
```
root@<containerID>:~/project# ssh-keygen -t rsa
root@<containerID>:~/project# vi ~/.ssh/id_rsa.pub
```
Then, copy the content of vim editor, as SSH Key. Upload the SSH Key in your 'Setting' in github account.
After SSH Key is uploaded in your personal account, our github repository will be accessable.

***
