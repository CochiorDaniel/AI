import map as m


class State:
    def __init__(self, i, j):
        self.cell = (i, j)
        if i-1-m.harta[i][j] >= 0:
            self.up = (i-1-m.harta[i][j], j)
        else:
            self.up = (0, j)

        if 0 <= i+1-m.harta[i][j] < 7:
            self.down = (i+1-m.harta[i][j], j)
        else:
            if i+1-m.harta[i][j] < 0:
                self.down = (0, j)
            if i+1-m.harta[i][j] >= 7:
                self.down = (6, j)

        if i-m.harta[i][j] >= 0 and j+1 < 10:
            self.right = (i-m.harta[i][j], j+1)
        else:
            if i-m.harta[i][j] < 0 and j+1 < 10:
                self.right = (0, j+1)
            if i-m.harta[i][j] < 0 and j+1 >= 10:
                self.right = (0, 9)
            if i-m.harta[i][j] >= 0 and j+1 >= 10:
                self.up = (i-m.harta[i][j], 9)

        if i-m.harta[i][j] >= 0 and j-1 >= 0:
            self.left = (i-m.harta[i][j], j-1)
        else:
            if i - m.harta[i][j] < 0 and j - 1 < 0:
                self.right = (0, 0)
            if i - m.harta[i][j] < 0 and j - 1 >= 0:
                self.right = (0, j-1)
            if i - m.harta[i][j] >= 0 and j - 1 >= 0:
                self.up = (i - m.harta[i][j], j-1)

        self.act = {"up": self.up, "down": self.down, "right": self.right, "left": self.left}

        if i != 3 and j != 7:
            self.reward = -1
        else:
            self.reward = 9999

