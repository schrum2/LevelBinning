import matplotlib.pyplot as plt
import numpy as np
import argparse

def read_log(log):
    f = open(log,'r')

    header=['Tick', 'Total','QD Score','Maximum Fitness Score','Discarded','Restricted', 'Restricted QD Score','Restricted Maximum Fitness Score']

    log_lines = f.readlines()
    column_1 = []
    column_2 = []
    column_3 = []
    column_4 = []
    column_5 = []
    column_6 = []
    column_7 = []
    column_8 = []

    for line in log_lines:
        column_data = line.split("\t")
        column_1.append(int(column_data[0].strip()))
        column_2.append(int(column_data[1]))
        column_3.append(float(column_data[2]))
        column_4.append(float(column_data[3]))
        column_5.append(int(column_data[4]))
        column_6.append(int(column_data[5]))
        column_7.append(float(column_data[6]))
        column_8.append(float(column_data[7]))

    return header, column_1, column_2, column_3, column_4, column_5, column_6, column_7, column_8

def make_plot(title, xlabel, x_data, y1label,y_data1, y2label = None,y_data2=None, file_title=None):
    # Plot Title
    plt.title(title)

    # X Axis 
    plt.xlabel(xlabel)
    plt.xlim(min(x_data),max(x_data))

    # Y Axis 
    plt.ylabel(y1label)
    plt.ylim(min(y_data1), max(y_data1))

    plt.plot(x_data, y_data1, '-', linewidth=2, markersize=12,color='red', label=y1label)
    if y2label is not None:
        plt.plot(x_data, y_data2, '--', linewidth=2, markersize=12,color='blue', label=y2label)

    plt.legend()
    plt.savefig(file_title)
    plt.show()

# The first plot:
# File: Handle_FillWithDiscarded_log.pdf
#       Handle_Fill_log.txt" u 1:($2 / 6500) w linespoints t "Total"
#       Handle_Fill_log.txt" u 1:5 w linespoints t "Discarded"
def make_plot_1(xlabel,column_1, y1label,column_2, y2label,y_data2):
    handle='ZeldaDungeonsWallWaterRooms-FullDirect2GAN0'
    title = f"Archive Filled Bins"
    file_save=f"{handle}_FillWithDiscarded_log.pdf"

    x_data = column_1
    y_data1 = column_2
    y_data1_div6500 = [x / float(6500.0) for x in y_data1]
    y_data2 = column_5
    
    make_plot(title,xlabel,x_data, y1label,y_data1_div6500, y2label,y_data2, file_save)


# The Second plot:
# File: Handle_FillPercentage_log.pdf
# Handle_Fill_log.txt" u 1:($2 / 6500) w linespoints t "Total"
def make_plot_2(xlabel,column_1, y1label,y_data1_div6500, y2label,y_data2):
    handle="ZeldaDungeonsWallWaterRooms-FullDirect2GAN0"
    title=f"Archive Filled Bins Percentage"
    file_save=f"{handle}_FillPercentage_log.pdf"

    xlabel=header[0]
    y1label=header[1]

    x_data = column_1
    y_data1 = column_2
    y_data1_div6500 = [x / float(6500.0) for x in y_data1]

    make_plot(title,xlabel,x_data, y1label,y_data1_div6500, None, None, file_save)

# The third plot:
# File: Handle_Fill_log.pdf
# Handle_Fill_log.txt u 1:2 w linespoints t "Total", \
# Handle_Fill_log.txt" u 1:6 w linespoints t "Restricted"
def make_plot_3(xlabel,column_1, y1label,column_2, y2label,column_6):
    handle="ZeldaDungeonsWallWaterRooms-FullDirect2GAN0"
    title="Archive Filled Bins"
    file_save=f"{handle}_Fill_log.pdf"

    make_plot(title,xlabel,column_1, y1label,column_2, y2label,column_6, file_save)

# The fourth plot:
# File: Handle_QD_log.pdf
# Handle_Fill_log.txt" u 1:3 w linespoints t "QD Score", \
# Handle_Fill_log.txt" u 1:7 w linespoints t "Restricted QD Score"
def make_plot_4(xlabel,column_1, y1label,column_3, y2label,column_7):
    handle="ZeldaDungeonsWallWaterRooms-FullDirect2GAN0"
    title=f"Archive QD Scores"
    file_save = f"{handle}_QD_log.pdf"

    make_plot(title,xlabel,column_1, y1label,column_3, y2label,column_7, file_save)


# The fifth plot:
# File: Handle_Maximum_log.pdf"
# Handle_Fill_log.txt" u 1:4 w linespoints t "Maximum Fitness Score", \
# Handle_Fill_log.txt" u 1:8 w linespoints t "Restricted Maximum Fitness Score"
def make_plot_5(xlabel,column_1, y1label,column_4, y2label,column_8):
    handle="ZeldaDungeonsWallWaterRooms-FullDirect2GAN0"
    title=f"Maximum individual fitness score"
    file_save=f"{handle}_Maximum_log.pdf"
    
    make_plot(title,xlabel,column_1, y1label,column_4, y2label,column_8, file_save)





args = [
        ("file", {"type": str, "help":"path of the results log."}),
        ]

parser = argparse.ArgumentParser(description='Plot results.')

for name, opts in args:
    parser.add_argument(name, **opts)

## log="/Users/amy/research/jacob/JacobCode_09_01_22/LevelBinning/zeldadungeonswallwaterrooms/FullDirect2GAN0/ZeldaDungeonsWallWaterRooms-FullDirect2GAN0_Fill_log.txt"

if __name__ == "__main__":
    args = parser.parse_args()
    file_path = args.file

    header, column_1, column_2, column_3, column_4, column_5, column_6, column_7, column_8 = read_log(file_path)

    make_plot_1(header[0],column_1, header[1],column_2, header[4],column_5)
    make_plot_2(header[0],column_1, header[1],column_1, header[5],column_5)
    make_plot_3(header[0],column_1, header[1],column_2, header[5],column_6)
    make_plot_4(header[0],column_1, header[2],column_3, header[6],column_7)
    make_plot_5(header[0],column_1, header[3],column_4, header[7],column_8)


