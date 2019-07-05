#include <sys/types.h>
#include <sys/dir.h>
#include <sys/param.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <dirent.h>
#include <pwd.h>
#include <grp.h>
#include <time.h>
#include <locale.h>
#include <langinfo.h>
#include <stdint.h>
#include <fcntl.h>
#include <string.h>

static char perms_buff[30];

const char *get_perms(mode_t mode)
{
  char ftype = '?';

  if (S_ISREG(mode)) ftype = '-';
  if (S_ISLNK(mode)) ftype = 'l';
  if (S_ISDIR(mode)) ftype = 'd';
  if (S_ISBLK(mode)) ftype = 'b';
  if (S_ISCHR(mode)) ftype = 'c';
  if (S_ISFIFO(mode)) ftype = '|';

  sprintf(perms_buff, "%c%c%c%c%c%c%c%c%c%c %c%c%c", ftype,
  mode & S_IRUSR ? 'r' : '-',
  mode & S_IWUSR ? 'w' : '-',
  mode & S_IXUSR ? 'x' : '-',
  mode & S_IRGRP ? 'r' : '-',
  mode & S_IWGRP ? 'w' : '-',
  mode & S_IXGRP ? 'x' : '-',
  mode & S_IROTH ? 'r' : '-',
  mode & S_IWOTH ? 'w' : '-',
  mode & S_IXOTH ? 'x' : '-',
  mode & S_ISUID ? 'U' : '-',
  mode & S_ISGID ? 'G' : '-');

  return (const char *)perms_buff;
}

char pathname[MAXPATHLEN];

void die(char *msg)
{
  perror(msg);
  exit(0);
}

static int
one (const struct dirent *unused)
{
  return 1;
}

int main()
{
  int count,i;
  struct direct **files;
  struct stat statbuf;
  char datestring[256];
  struct passwd pwent;
  struct passwd *pwentp;
  struct group grp;
  struct group *grpt;
  struct tm time;
  char buf[1024];

// get pathname of the working dicretory
  if(!getcwd(pathname, sizeof(pathname)))
    die("Error getting pathname");

// scandir return a array of file or folder in directory
  count = scandir(pathname, &files, one, alphasort);

// calculate total for all file in directory
  int total=0;
  for(int i=0;i<count;i++){
    if (stat(files[i]->d_name, &statbuf) == 0  && (strcmp(".",files[i]->d_name)!=0 && strcmp("..",files[i]->d_name)!=0))
       total+=statbuf.st_blocks/2;
  }
    printf("total %d\n",total);


// if directory have more than 1 file or folder in directory
  if(count > 0)
  {
    
    // each file or folder in directory
    for (i=0; i<count; ++i)
    {
      // stat: function return information about a file, if it return 0: success
      // pass file . or ..
      if (stat(files[i]->d_name, &statbuf) == 0 && (strcmp(".",files[i]->d_name)!=0 && strcmp("..",files[i]->d_name)!=0))
      {
        /* Print out type, permissions, and number of links. */
        printf("%10.10s", get_perms(statbuf.st_mode));
        // st_nlink: number of hard links
        printf(" %d  ", statbuf.st_nlink); 


        // getpwuid: get information about a user with the specified uid
        // st_uid: user id of owner
        // pw_name: username
        if (!getpwuid_r(statbuf.st_uid, &pwent, buf, sizeof(buf), &pwentp))
          printf(" %s ", pwent.pw_name);
        else
          printf(" %d ", statbuf.st_uid);

        // getgrgid: funtion return a pointer to structure containing the broken-out field of the record in the group db matches the group id gid
        // gr_name: group name
        if (!getgrgid_r (statbuf.st_gid, &grp, buf, sizeof(buf), &grpt))
          printf(" %s  ", grp.gr_name);
        else
          printf(" %d  ", statbuf.st_gid);

        /* Print size of file. */
        printf(" %5d ", (int)statbuf.st_size);

        localtime_r(&statbuf.st_mtime, &time);
        /* Get localized date string. */
        strftime(datestring, sizeof(datestring), "%b %d %H:%M", &time);

        printf(" %s", datestring);

        // if statbuf's mode is directory: color is blue
        if (S_ISDIR(statbuf.st_mode)) 
              printf("\033[1;34m");

        printf(" %s \n",files[i]->d_name);

        // reset color
        printf("\033[0m");
      }

      free (files[i]);
    }

    free(files);
  }
}
