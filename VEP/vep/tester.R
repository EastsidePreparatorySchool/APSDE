setwd("C:/Users/HSamuelson/Documents/GitHub/APSDE/VEP/vep/")
args <- commandArgs(trailingOnly = TRUE)
#rnorm(n=as.numeric(args[1]), mean=as.numeric(args[2]))
print("starting")
#print(as.numeric(args[1]) * as.numeric(args[2]))
print("ending")

#
# Henry Samuelson 4/25/17
#
#Reading CSV and returning data
print(getwd())
dataBase <- read.csv("idDatabase (1).csv")
votes <- read.csv("votesTEMP.csv")

#arg1 -- id
#arg2 --"lastfirst"
#arg3 -- wanting to vote, takes id of the canadate 

#Check if ID and name exsist and match
idprocess <- function(idq, nameq, votingID){
  print("Starting funct call")
  print(typeof(votingID))
  final <- numeric(0) #will be the only thing the function returns
  
  #check if id exsists in order not to mess up the subset()
  idexsists <- 0
  for(i in 1:length(dataBase$id)){
    if(dataBase$id[i] == idq) {
      idexsists <- 1
    }
  }
  print("forloop1")
  if(idexsists != 1) {
    return("NoID")
  }
  if(tryCatch( subset(dataBase, dataBase$id == idq)$name == nameq)) {
    #Cast ballot
    print("First cathc")
    if(as.double(subset(dataBase, dataBase$id == idq)$hasvoted) == 0){#make sure they havent already voted 
      if(as.numeric(votingID) != as.numeric(0)) { #means that they want to vote
        print("hit int 1")
        votes[as.numeric(votingID)] = votes[as.numeric(votingID)] + 1
        #print(votes)
        #Bc apparently R has trouble actually writing to to files #heres an easy hack
        #write over the file and then reload it.
        write.csv(votes, file = "votesTEMP.csv")
        votes <- read.csv("votesTEMP.csv")
        print("VOTES")
        print(votes)
      }
      
      
      #user has voted change has voted column
      dataBase[subset(dataBase, dataBase$id == idq)$easyIndex, ]$hasvoted <- 1
      print(dataBase$hasvoted)
      print("Update")
      write.csv(dataBase, file = "idDatabase (1).csv")
      dataBase <- read.csv("idDatabase (1).csv")
      print(dataBase$hasvoted)
      return("yah")
    }
    #return("yah")
    #sssssprint("y")
    
  } else{
    return("nah")
  } #print("n")
  
  
  
  
}


idprocess(args[1], args[2], args[3])

