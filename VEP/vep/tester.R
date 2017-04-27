setwd("C:/Users/HSamuelson/Documents/GitHub/APSDE/VEP/vep/")
args <- commandArgs(trailingOnly = TRUE)
#rnorm(n=as.numeric(args[1]), mean=as.numeric(args[2]))
print("starting")
#print(as.numeric(args[1]) * as.numeric(args[2]))
print("ending")

#
# Henry Samuelson
#
#Reading CSV and returning data
print(getwd())
dataBase <- read.csv("idDatabase (1).csv")
votes <- read.csv("votesTEMP.csv")

#arg1 -- id
#arg2 --"lastfirst"
#arg3 -- wanting to vote?

#Check if ID and name exsist and match
idprocess <- function(idq, nameq, votingID){

  final <- numeric(0)
 
  #check if id exsists in order not to mess up the subset()
  idexsists <- 0
  for(i in 1:length(dataBase$id)){
    if(dataBase$id[i] == idq) {
      idexsists <- 1
    }
  }
  
  if(idexsists != 1) {
    return("NoID")
  }
  if(tryCatch( subset(dataBase, dataBase$id == idq)$name == nameq)) {
    #Cast ballot
    if((subset(dataBase, dataBase$id == idq)$hasVoted == 0) && votingID != 0){
      print("hit int 1")
      votes[votingID] = votes[votingID] + 1
      
      #user has voted change has voted column
      dataBase[subset(dataBase, dataBase$id == idq)$easyIndex, ]$hasvoted = 1
    }
    return("yah")
    #sssssprint("y")
    
  } else{
    return("nah")
  } #print("n")
  
  

  
}
  

idprocess(args[1], args[2], args[3])

