#setwd("C:/Users/HSamuelson/Documents/GitHub/APSDE/VEP/vep/")
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

  #To print final stats id has to == 666 and login has to be "sixsixsix"

dataBase <- read.csv("idDatabase2.csv")
dataBase$X <- NULL
votes <- read.csv("votesTEMP.csv")
votes$X <- NULL

#arg1 -- id  <- your key you are sent via email.... (THIS IS NO LONGER STUDENT ID, due to security issues)
#arg2 --"login"  <- hsamuelson
#arg3 -- wanting to vote?

#for returning final stats to gummre
returnFinalStats <- function(){
  
  #Percent of males who voted
  percent.male <- as.numeric(
    length(
      subset(
        subset(dataBase, dataBase$hasvoted == 1),
        subset(dataBase, dataBase$hasvoted == 1)$gender == "M")[,1])
    ) / as.numeric(
      length(
        subset(dataBase, dataBase$gender == "M")[,1])
      )
  
  percent.female <-as.numeric(
    length(
      subset(
        subset(dataBase, dataBase$hasvoted == 1),
        subset(dataBase, dataBase$hasvoted == 1)$gender == "F")[,1])
    ) / as.numeric(
      length(
        subset(dataBase, dataBase$gender == "F")[,1])
      )
  percents <- cbind(percent.female, percent.male)
  
  png("src/finalstatimages/pMFvote.png")  #write Image to png
  print("W")
  barplot(percents, main = "Percent of Male and female votes", ylab = "%")
  dev.off()
  
  #Actual number of votes male female
  number.of.male.votes <- as.numeric(length(subset(subset(dataBase, dataBase$hasvoted == 1),
                                                   subset(dataBase, dataBase$hasvoted == 1)$gender == "M")[,1]))
  
  number.of.female.votes <- as.numeric(length(subset(subset(dataBase, dataBase$hasvoted == 1), 
                                                     subset(dataBase, dataBase$hasvoted == 1)$gender == "F")[,1]))
  
  comb.number <- cbind(number.of.female.votes, number.of.male.votes)
  
  png("src/finalstatimages/numVotesMF.png")
  print("WW")
  barplot(comb.number, main = "Total # of Votes M/F", ylab = "# of Votes")
  dev.off()
  
  
  #Votes by grade
  png("src/finalstatimages/votesByGrade.png")
  print("WWWW")
  barplot(table(subset(dataBase, dataBase$hasvoted == 1)$gradyear),  main = "Votes per grade", ylab ="# of Votes")
  dev.off()
  
}  



#Check if ID and name exsist and match
idprocess <- function(idq, nameq, votingID){
  if(idq == "666"){
    if(nameq == "sixsixsix"){  #Return final stats to Gumere when id and last name == 666
      returnFinalStats()
      return("666")
    }
  }
  #check if id exsists in order not to mess up the subset()
  idexsists <- 0
  for(i in 1:length(dataBase$id)){
    if(dataBase$id[i] == idq) {
      idexsists <- 1
    }
  }
  #If the ID is invalid return
  if(idexsists != 1) {
    return(0)
  }
  #ew now know the ID is valid so we can proporly try it tp see if the it matches the name
  if(tryCatch( subset(dataBase, dataBase$id == idq)$login == nameq)) {

    if(votingID == 0) {
      return(1)
    }
    counter <- 0
    if(as.double(subset(dataBase, dataBase$id == idq)$hasvoted) == 0){ #If the row in hasVoted is 0 where the id in the row is equal to the inputed id.
      if(as.numeric(votingID) != as.numeric(0)) { #If there is a choce that is not zero, i.e. there is a canadite choice
                                                  #This is here becasue if the function is called for a initial login a zero will be inputed for the canadate id
        votes[as.numeric(votingID)] = votes[as.numeric(votingID)] + 1 #Change the row +1 where the id matches input id.
        counter = counter + 1
        write.csv(votes, file = "votesTEMP.csv")
        votes <- read.csv("votesTEMP.csv")
        votes$X <- NULL
      }
      
      
      #user has voted change has voted column
      dataBase[subset(dataBase, dataBase$id == idq)$id, ]$hasvoted = 1
      counter = counter +1
      write.csv(dataBase, file = "idDatabase2.csv")
      dataBase <- read.csv("idDatabase2.csv")
      data$X <- NULL 
      if(counter ==2){
        return(1)  #If this is true that means the vote has been sucessfuly added and users "hasvoted" column has been updated.
      }
    }
  } else{
    return(0)
  } 
}


idprocess(args[1], args[2], args[3])  #Actual function callfor those who dont know R our script starts here.

