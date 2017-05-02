# setwd("C:/Users/HSamuelson/Documents/GitHub/APSDE/VEP/vep/")
args <- commandArgs(trailingOnly = TRUE)

#
# Henry Samuelson 4/25/17
#
#Reading CSV and returning data


#How to use guide
  #If you input something for the first two inputs and a zero for
  #the thrid input the program will assume you are using it to check
  #for the inital login. if the id is invalid it will return "0". If the id
  #and the LastnameFirstName do not match then return "0", if they do match,
  #then return "1".

  #If you supply a third value which is 1 or 2 then will check if you have 
  #voted already or not. if you have it will return "0". If you havent voted
  #It will change your hasVoted column in IdDatabase, and will add one to the
  #candates column in the votesTEMP.csv file. the program will then return "1".
#print(getwd())
dataBase <- read.csv("idDatabase (1).csv")
votes <- read.csv("votesTEMP.csv")

#arg1 -- id
#arg2 --"lastfirst"
#arg3 -- wanting to vote?

#Check if ID and name exsist and match
idprocess <- function(idq, nameq, votingID){
  #print("Starting funct call")
  #print(typeof(votingID))
  #final <- c(0,0)
 
  #check if id exsists in order not to mess up the subset()
  idexsists <- 0
  for(i in 1:length(dataBase$id)){
    if(dataBase$id[i] == idq) {
      idexsists <- 1
    }
  }
  #print("forloop1")
  if(idexsists != 1) {
    return(0)
  }
  if(tryCatch( subset(dataBase, dataBase$id == idq)$name == nameq)) {

    if(votingID == 0) {
      return(1)
    }
    counter <- 0
    if(as.double(subset(dataBase, dataBase$id == idq)$hasvoted) == 0){
      if(as.numeric(votingID) != as.numeric(0)) {
        #print("hit int 1")
        votes[as.numeric(votingID)] = votes[as.numeric(votingID)] + 1
        counter = counter + 1
      }
      
      
      #user has voted change has voted column
      dataBase[subset(dataBase, dataBase$id == idq)$easyIndex, ]$hasvoted = 1
      counter = counter +1
      if(counter ==2){
        return(1)
      }
    }
    #return("yah")
    #print("y")
    
  } else{
    return(0)
  } #print("n")
  
  

  
}
  

idprocess(args[1], args[2], args[3])

