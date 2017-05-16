#Why this has to be in a seperate file i dont fucking know but i spent the last
#three hours trying to understand why, independenly calling it works but calling it inside of
#ANY I REPEAT ANY LOOP DOESNT WORK, ITS 1:00 AM!?!?!>?> 
dataBase <- read.csv("idDatabase2.csv")
dataBase$X <- NULL
votes <- read.csv("votesTEMP.csv")
votes$X <- NULL