import random

import numpy as np
import state

actiuni = ["up", "down", "right", "left"]


class Qmap:
    def __init__(self):
        self.qmap = [[{'up': 0, 'down': 0, 'right': 0, 'left': 0} for _ in range(10)] for _ in range(7)]
        self.learning_rate = 0.5
        self.discount_factor = 0.7
        self.nr_episoade = 1000
        self.vizitati=[]
        self.max_depth = 500

    def learn(self, s):
        while self.nr_episoade > 0:
            self.update_q(s)
            print(self.max_depth)
            self.max_depth = 500
            self.vizitati.clear()
            self.nr_episoade -= 1

    def update_q(self, s):
        self.vizitati.append(s.cell)
        if s.cell == (3, 7) or self.max_depth == 0:
            if s.cell == (3, 7):
                return 9999
            if self.max_depth == 0:
                return 1

        else:
            self.max_depth -= 1
        global directie
        directii = dict()
        maxim = -9999
        for a in actiuni:
            if a in s.act:
                next_pos = s.act[a]
                value = self.qmap[next_pos[0]][next_pos[1]][a]
                if value > maxim and state.State(next_pos[0],next_pos[1]).cell not in self.vizitati:
                    maxim = value
        for a in actiuni:
            if a in s.act:
                next_pos = s.act[a]
                value = self.qmap[next_pos[0]][next_pos[1]][a]
                if value == maxim:
                    directii[a] = state.State(next_pos[0], next_pos[1])

        if not directii:
            return 0

        directie, state2 = random.choice(list(directii.items()))
        # print("Am ales directia", directie)
        # print("Next state:", state2.cell)
        print(state2.cell)

        if self.max_depth > 0:
            self.qmap[state2.cell[0]][state2.cell[1]][directie] += self.learning_rate * (
                    state2.reward + self.discount_factor * self.update_q(state2) -
                    self.qmap[state2.cell[0]][state2.cell[1]][directie])

        return self.qmap[state2.cell[0]][state2.cell[1]][directie]
